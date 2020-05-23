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
 * DataMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class DataMarshaller {

    private static final MarshallingInfo<StructuredPojo> MMR_BINDING = MarshallingInfo.builder(MarshallingType.STRUCTURED)
            .marshallLocation(MarshallLocation.PAYLOAD).marshallLocationName("mmr").build();
    private static final MarshallingInfo<List> PLATES_BINDING = MarshallingInfo.builder(MarshallingType.LIST).marshallLocation(MarshallLocation.PAYLOAD)
            .marshallLocationName("plates").build();

    private static final DataMarshaller instance = new DataMarshaller();

    public static DataMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(Data data, ProtocolMarshaller protocolMarshaller) {

        if (data == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
            protocolMarshaller.marshall(data.getMmr(), MMR_BINDING);
            protocolMarshaller.marshall(data.getPlates(), PLATES_BINDING);
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
