/**
 * null
 */
package hu.arh.anprcloud.client.model.transform;

import java.util.List;
import javax.annotation.Generated;

import com.amazonaws.SdkClientException;
import hu.arh.anprcloud.client.model.*;

import com.amazonaws.protocol.*;
import com.amazonaws.annotation.SdkInternalApi;

/**
 * PlatesElementMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class PlatesElementMarshaller {

    private static final MarshallingInfo<StructuredPojo> BGCOLOR_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("bgcolor").build();
    private static final MarshallingInfo<StructuredPojo> COLOR_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("color").build();
    private static final MarshallingInfo<Integer> CONFIDENCE_BINDING = MarshallingInfo.builder(MarshallingType.INTEGER)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("confidence").build();
    private static final MarshallingInfo<Integer> PLATETYPE_BINDING = MarshallingInfo.builder(MarshallingType.INTEGER)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("plateType").build();
    private static final MarshallingInfo<String> COUNTRY_BINDING = MarshallingInfo.builder(MarshallingType.STRING)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("country").build();
    private static final MarshallingInfo<String> STATE_BINDING = MarshallingInfo.builder(MarshallingType.STRING)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("state").build();
    private static final MarshallingInfo<List> PLATECHARS_BINDING = MarshallingInfo.builder(MarshallingType.LIST).marshallLocation(MarshallLocation.PAYLOAD)
            .marshallLocationName("plateChars").build();
    private static final MarshallingInfo<StructuredPojo> PLATEROI_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("plateROI").build();
    private static final MarshallingInfo<Integer> PROCTIME_BINDING = MarshallingInfo.builder(MarshallingType.INTEGER)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("proctime").build();
    private static final MarshallingInfo<String> UNICODETEXT_BINDING = MarshallingInfo.builder(MarshallingType.STRING)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("unicodeText").build();

    private static final PlatesElementMarshaller instance = new PlatesElementMarshaller();

    public static PlatesElementMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(PlatesElement platesElement, ProtocolMarshaller protocolMarshaller) {

        if (platesElement == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(platesElement.getBgcolor(), BGCOLOR_BINDING);
            protocolMarshaller.marshall(platesElement.getColor(), COLOR_BINDING);
            protocolMarshaller.marshall(platesElement.getConfidence(), CONFIDENCE_BINDING);
            protocolMarshaller.marshall(platesElement.getPlateType(), PLATETYPE_BINDING);
            protocolMarshaller.marshall(platesElement.getCountry(), COUNTRY_BINDING);
            protocolMarshaller.marshall(platesElement.getState(), STATE_BINDING);
            protocolMarshaller.marshall(platesElement.getPlateChars(), PLATECHARS_BINDING);
            protocolMarshaller.marshall(platesElement.getPlateROI(), PLATEROI_BINDING);
            protocolMarshaller.marshall(platesElement.getProctime(), PROCTIME_BINDING);
            protocolMarshaller.marshall(platesElement.getUnicodeText(), UNICODETEXT_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
