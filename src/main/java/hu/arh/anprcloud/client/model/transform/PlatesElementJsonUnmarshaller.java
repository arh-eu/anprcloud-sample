/**
 * null
 */
package hu.arh.anprcloud.client.model.transform;

import java.math.*;

import javax.annotation.Generated;

import hu.arh.anprcloud.client.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;

import com.fasterxml.jackson.core.JsonToken;
import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * PlatesElement JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class PlatesElementJsonUnmarshaller implements Unmarshaller<PlatesElement, JsonUnmarshallerContext> {

    public PlatesElement unmarshall(JsonUnmarshallerContext context) throws Exception {
        PlatesElement platesElement = new PlatesElement();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return null;
        }

        while (true) {
            if (token == null)
                break;

            if (token == FIELD_NAME || token == START_OBJECT) {
                if (context.testExpression("bgcolor", targetDepth)) {
                    context.nextToken();
                    platesElement.setBgcolor(BgcolorJsonUnmarshaller.getInstance().unmarshall(context));
                }
                if (context.testExpression("color", targetDepth)) {
                    context.nextToken();
                    platesElement.setColor(ColorJsonUnmarshaller.getInstance().unmarshall(context));
                }
                if (context.testExpression("confidence", targetDepth)) {
                    context.nextToken();
                    platesElement.setConfidence(context.getUnmarshaller(Integer.class).unmarshall(context));
                }
                if (context.testExpression("plateType", targetDepth)) {
                    context.nextToken();
                    platesElement.setPlateType(context.getUnmarshaller(Integer.class).unmarshall(context));
                }
                if (context.testExpression("country", targetDepth)) {
                    context.nextToken();
                    platesElement.setCountry(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("state", targetDepth)) {
                    context.nextToken();
                    platesElement.setState(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("plateChars", targetDepth)) {
                    context.nextToken();
                    platesElement.setPlateChars(new ListUnmarshaller<PlateCharsElement>(PlateCharsElementJsonUnmarshaller.getInstance()).unmarshall(context));
                }
                if (context.testExpression("plateROI", targetDepth)) {
                    context.nextToken();
                    platesElement.setPlateROI(PlateROIJsonUnmarshaller.getInstance().unmarshall(context));
                }
                if (context.testExpression("proctime", targetDepth)) {
                    context.nextToken();
                    platesElement.setProctime(context.getUnmarshaller(Integer.class).unmarshall(context));
                }
                if (context.testExpression("unicodeText", targetDepth)) {
                    context.nextToken();
                    platesElement.setUnicodeText(context.getUnmarshaller(String.class).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return platesElement;
    }

    private static PlatesElementJsonUnmarshaller instance;

    public static PlatesElementJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new PlatesElementJsonUnmarshaller();
        return instance;
    }
}
