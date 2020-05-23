/*
 *
 */
package hu.arh.anprcloud.client;

import com.amazonaws.Protocol;
import com.amazonaws.SdkClientException;
import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import com.amazonaws.opensdk.retry.RetryPolicyBuilder;
import com.amazonaws.retry.v2.RetryPolicyContext;
import com.amazonaws.util.RuntimeHttpUtils;
import hu.arh.anprcloud.client.model.ANPRCloudRequest;
import hu.arh.anprcloud.client.model.ANPRCloudResult;
import hu.arh.anprcloud.client.model.ANPRCloudServiceException;
import hu.arh.anprcloud.client.model.InternalServerErrorException;
import hu.arh.anprcloud.client.model.RequestTimeoutException;
import hu.arh.anprcloud.client.model.ServiceUnavailableException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author laszlo.toth
 */
public class ANPRCloudTest {

    private static final int THREAD_COUNT = 100;
    private static final int TASK_COUNT = 1000;
    private static final int RETRY_COUNT = 10;
    private static final ExecutorService taskPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static final ExecutorService processPool = Executors.newFixedThreadPool(THREAD_COUNT * 5);

    static {
        try {
            File currentDir = new File(ANPRCloudTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            System.setProperty("log4j.configurationFile", currentDir.getAbsolutePath().concat(File.separator).concat("log4j.properties"));
        } catch (URISyntaxException e) {
            e.printStackTrace(System.err);
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(ANPRCloudTest.class);
    private static final String ELAPSED_TIME_WITHHOUR = "%1$d:%2$02d:%3$02d.%3$03d";
    private static final String ELAPSED_TIME = "%1$02d:%2$02d.%3$03d";

    public static void main(String[] args) throws Exception {
        String ak = null;
        File imgs = null;
        if (args.length >= 2) {
            ak = args[0];
            imgs = new File(args[1]);
            imgs = imgs.exists() && imgs.isDirectory() ? imgs : null;
        }
        if (ak == null || imgs == null) {
            LOGGER.error("Usage: ANPRCloudTest <api key> <image folder>");
            return;
        }

        Map<Integer, AtomicInteger> retries = new HashMap<Integer, AtomicInteger>() {
            {
                for (int i = 1; i <= RETRY_COUNT; i++) {
                    put(i, new AtomicInteger(0));
                }
            }
        };

        final String apiKey = ak;
        final List<Path> imageFolders = Files.list(imgs.toPath()).collect(Collectors.toList());

        ANPRCloudConfig.getConfig()
                .endpoint(RuntimeHttpUtils.toUri("api-eu.anpr-cloud.com", Protocol.HTTPS))
                .stage("/dev")
                .withThrottledRetries(false);

        ANPRCloudService service = ANPRCloudService.builder()
                .connectionConfiguration(new ConnectionConfiguration()
                        .maxConnections(1000)
                        .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(new TimeoutConfiguration()
                        .httpRequestTimeout(5000)
                        .totalExecutionTimeout(90000)
                        .socketTimeout(5000))
                .retryPolicy(RetryPolicyBuilder.standard()
                        .retryOnExceptions(
                                SdkClientException.class,
                                ServiceUnavailableException.class,
                                RequestTimeoutException.class,
                                InternalServerErrorException.class)
                        .maxNumberOfRetries(RETRY_COUNT)
                        .backoffStrategy((RetryPolicyContext context) -> {
                            AtomicInteger i = retries.get(context.totalRequests());
                            if (i != null) {
                                i.incrementAndGet();
                            }
                            LOGGER.log(Level.DEBUG, "{}. retry: {} ({}) - {}", context.totalRequests(), context.exception(),
                                    context.exception() instanceof ANPRCloudServiceException ? ((ANPRCloudServiceException) context.exception()).getError() : context.exception().getMessage(),
                                    context.originalRequest());
                            return (int) ((Math.random() + context.totalRequests()) * 100);
                        })
                        .build())
                .apiKey(apiKey).build();

        AtomicInteger summaAnprNodetime = new AtomicInteger(0);
        AtomicInteger countAnprNodetime = new AtomicInteger(0);
        AtomicInteger summaMmrNodetime = new AtomicInteger(0);
        AtomicInteger countMmrNodetime = new AtomicInteger(0);
        AtomicInteger summaAnprProctime = new AtomicInteger(0);
        AtomicInteger countAnprProctime = new AtomicInteger(0);
        AtomicInteger summaMmrProctime = new AtomicInteger(0);
        AtomicInteger countMmrProctime = new AtomicInteger(0);
        AtomicInteger recognitionFailures = new AtomicInteger(0);
        AtomicInteger noPlateFailures = new AtomicInteger(0);
        AtomicInteger failures = new AtomicInteger(0);
        AtomicInteger success = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        AtomicLong summa = new AtomicLong(0);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final String threadName = "Thread" + (i + 1);
            Runnable threadTask = () -> {
                for (int n = 0; n < TASK_COUNT; n++) {
                    final int threadCount = n + 1;
                    Runnable processTask = () -> {
                        try {
                            process(service, imageFolders, threadName, threadCount, summa,
                                    summaAnprNodetime, countAnprNodetime,
                                    summaMmrNodetime, countMmrNodetime,
                                    summaAnprProctime, countAnprProctime,
                                    summaMmrProctime, countMmrProctime,
                                    success, failures, recognitionFailures, noPlateFailures);
                        } catch (Throwable th) {
                            LOGGER.log(Level.ERROR, "Error occured", th);
                        }
                    };
                    processPool.execute(processTask);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.ERROR, "{} {}. call - InterruptedException", threadName, n + 1);
                    }
                }
                LOGGER.log(Level.INFO, "{} finished", threadName);
            };
            taskPool.execute(threadTask);
        }
        taskPool.shutdown();
        while (!taskPool.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
        }
        processPool.shutdown();
        while (!processPool.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
        }
        LOGGER.log(Level.INFO, "Average ANPR nodetime on worker nodes ({} calls): {}", countAnprNodetime.get(), countAnprNodetime.get() > 0 ? formatElapsedTime(summaAnprNodetime.get() / countAnprNodetime.get()) : "N/A");
        LOGGER.log(Level.INFO, "Average MMR nodetime on worker nodes ({} calls): {}", countMmrNodetime.get(), countMmrNodetime.get() > 0 ? formatElapsedTime(summaMmrNodetime.get() / countMmrNodetime.get()) : "N/A");
        LOGGER.log(Level.INFO, "Average ANPR proctime on worker nodes ({} calls): {}", countAnprProctime.get(), countAnprProctime.get() > 0 ? formatElapsedTime(summaAnprProctime.get() / countAnprProctime.get()) : "N/A");
        LOGGER.log(Level.INFO, "Average MMR proctime on worker nodes ({} calls): {}", countMmrProctime.get(), countMmrProctime.get() > 0 ? formatElapsedTime(summaMmrProctime.get() / countMmrProctime.get()) : "N/A");
        LOGGER.log(Level.INFO, "Summa processing time: {}", formatElapsedTime(System.currentTimeMillis() - start));
        LOGGER.log(Level.INFO, "Average processing time: {}", success.get() + failures.get() > 0 ? formatElapsedTime(summa.get() / (success.get() + failures.get())) : "N/A");
        LOGGER.log(Level.INFO, "Success count: {}", success.get());
        LOGGER.log(Level.INFO, "Failure count: {}", failures.get());
        LOGGER.log(Level.INFO, "Recognition failure count: {} (no plate error: {})", recognitionFailures.get(), noPlateFailures.get());
        LOGGER.log(Level.INFO, "Retry count: {} {}", retries.values().stream().collect(Collectors.summingInt(i -> i.get())), retries);

        service.shutdown();
    }

    private static void process(ANPRCloudService service, List<Path> imageFolders, String threadName, int threadCount, AtomicLong summa,
            AtomicInteger summaAnprNodetime, AtomicInteger countAnprNodetime,
            AtomicInteger summaMmrNodetime, AtomicInteger countMmrNodetime,
            AtomicInteger summaAnprProctime, AtomicInteger countAnprProctime,
            AtomicInteger summaMmrProctime, AtomicInteger countMmrProctime,
            AtomicInteger success, AtomicInteger failures, AtomicInteger recognitionFailures, AtomicInteger noPlateFailures) {
        try {
            long now = System.currentTimeMillis();
            ANPRCloudRequest processRequest = new ANPRCloudRequest();
            processRequest.setWorkerName(threadName);
            processRequest.setCallNum(threadCount);
            Path imgDir = imageFolders.get((int) (imageFolders.size() * Math.random()));
            List<Path> imageFiles = Files.list(imgDir).collect(Collectors.toList());
            Path img = imageFiles.get((int) (imageFiles.size() * Math.random()));
            processRequest.setLocation(imgDir.toFile().getName());
            processRequest.setImage(img.toFile());
            processRequest.setType(threadCount % 2 == 0 ? ANPRCloudRequest.Type.ANPR : ANPRCloudRequest.Type.MMR);
            //processRequest.setType(ANPRCloudRequest.Type.ANPR);
            LOGGER.log(Level.INFO, "{} {}. call: {}", threadName, threadCount, processRequest);
            ANPRCloudResult result = service.process(processRequest);
            LOGGER.log(Level.INFO, "{} {}. call ({}): {}", threadName, threadCount, result.sdkResponseMetadata().requestId(), result.getAnswer());
            if (result.getAnswer() != null) {
                success.incrementAndGet();
                if (result.getAnswer().getNodetime() != null) {
                    if (processRequest.getType() == ANPRCloudRequest.Type.ANPR) {
                        summaAnprNodetime.addAndGet(result.getAnswer().getNodetime());
                        countAnprNodetime.incrementAndGet();
                    } else {
                        summaMmrNodetime.addAndGet(result.getAnswer().getNodetime());
                        countMmrNodetime.incrementAndGet();
                    }
                }
                if (result.getAnswer().getData() != null && result.getAnswer().getData().getPlates() != null && !result.getAnswer().getData().getPlates().isEmpty()) {
                    if (result.getAnswer().getData().getPlates().size() == 1
                            && result.getAnswer().getData().getPlates().get(0).getUnicodeText() == null
                            && result.getAnswer().getData().getPlates().get(0).getCountry() == null) {
                        noPlateFailures.incrementAndGet();
                        LOGGER.log(Level.INFO, "{} {}. call ({}): result doesn't contain any plates!", threadName, threadCount, result.sdkResponseMetadata().requestId());
                    } else {
                        AtomicBoolean found = new AtomicBoolean(false);
                        result.getAnswer().getData().getPlates().forEach((plate) -> {
                            String imgname = processRequest.getImage().getName();
                            imgname = imgname.indexOf('.') != -1 ? imgname.substring(0, imgname.indexOf('.')) : imgname;
                            if (imgname.equalsIgnoreCase(plate.getUnicodeText()) && plate.getCountry().toLowerCase().startsWith(processRequest.getLocation().toLowerCase())) {
                                found.set(true);
                            }
                            if (plate.getProctime() != null) {
                                summaAnprProctime.addAndGet(plate.getProctime());
                                countAnprProctime.incrementAndGet();
                            }
                        });
                        if (!found.get()) {
                            recognitionFailures.incrementAndGet();
                            LOGGER.log(Level.INFO, "{} {}. call ({}): Recognition failure!", threadName, threadCount, result.sdkResponseMetadata().requestId());
                        }
                        if (processRequest.getType() == ANPRCloudRequest.Type.MMR) {
                            if (result.getAnswer().getData().getMmr() != null && result.getAnswer().getData().getMmr().getProctime() != null) {
                                summaMmrProctime.addAndGet(result.getAnswer().getData().getMmr().getProctime());
                                countMmrProctime.incrementAndGet();
                            }
                        }
                    }
                } else {
                    noPlateFailures.incrementAndGet();
                    LOGGER.log(Level.INFO, "{} {}. call ({}): result doesn't contain 'Plates' block!", threadName, threadCount, result.sdkResponseMetadata().requestId());
                }
            } else {
                failures.incrementAndGet();
            }
            summa.addAndGet(System.currentTimeMillis() - now);
            LOGGER.log(Level.INFO, "{} {}. call ({}) - processing time: {}", threadName, threadCount, result.sdkResponseMetadata().requestId(), formatElapsedTime(System.currentTimeMillis() - now));
        } catch (ANPRCloudServiceException e) {
            LOGGER.log(Level.ERROR, "{} {}. call ({}) {} ({}): {}", threadName, threadCount, e.sdkHttpMetadata().requestId(), e.getClass().getSimpleName(), e.sdkHttpMetadata().httpStatusCode(), e.getError());
            failures.incrementAndGet();
        } catch (IOException | SdkClientException e) {
            LOGGER.log(Level.ERROR, threadName + " " + (threadCount) + ". call - error occured", e);
            failures.incrementAndGet();
        }
    }

    private static CharSequence formatElapsedTime(long elapsedMilliseconds) {

        long hours = 0;
        long minutes = 0;
        long seconds = 0;
        long milliseconds = 0;
        if (elapsedMilliseconds >= 60 * 60 * 1000) {
            hours = elapsedMilliseconds / (60 * 60 * 1000);
            elapsedMilliseconds -= hours * (60 * 60 * 1000);
        }
        if (elapsedMilliseconds >= 60 * 1000) {
            minutes = elapsedMilliseconds / (60 * 1000);
            elapsedMilliseconds -= minutes * (60 * 1000);
        }
        if (elapsedMilliseconds >= 1000) {
            seconds = elapsedMilliseconds / 1000;
            elapsedMilliseconds -= seconds * 1000;
        }
        milliseconds = elapsedMilliseconds;

        Formatter f = new Formatter(new StringBuilder(10),
                Locale.getDefault());
        if (hours > 0) {
            return f.format(ELAPSED_TIME_WITHHOUR, hours, minutes, seconds, milliseconds)
                    .toString();
        } else {
            return f.format(ELAPSED_TIME, minutes, seconds, milliseconds).toString();
        }
    }
}
