/**
 * null
 */
package hu.arh.anprcloud.client.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.amazonaws.protocol.StructuredPojo;
import com.amazonaws.protocol.ProtocolMarshaller;

/**
 * If the service call is successful, the service will return an Answer json object.
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/api-eu-2020-09-01T16:20:25Z/Answer" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class Answer implements Serializable, Cloneable, StructuredPojo {

    private Data data;

    private String node;

    private Integer nodetime;

    private String version;

    /**
     * @param data
     */

    public void setData(Data data) {
        this.data = data;
    }

    /**
     * @return
     */

    public Data getData() {
        return this.data;
    }

    /**
     * @param data
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Answer data(Data data) {
        setData(data);
        return this;
    }

    /**
     * @param node
     */

    public void setNode(String node) {
        this.node = node;
    }

    /**
     * @return
     */

    public String getNode() {
        return this.node;
    }

    /**
     * @param node
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Answer node(String node) {
        setNode(node);
        return this;
    }

    /**
     * @param nodetime
     */

    public void setNodetime(Integer nodetime) {
        this.nodetime = nodetime;
    }

    /**
     * @return
     */

    public Integer getNodetime() {
        return this.nodetime;
    }

    /**
     * @param nodetime
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Answer nodetime(Integer nodetime) {
        setNodetime(nodetime);
        return this;
    }

    /**
     * @param version
     */

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return
     */

    public String getVersion() {
        return this.version;
    }

    /**
     * @param version
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Answer version(String version) {
        setVersion(version);
        return this;
    }

    /**
     * Returns a string representation of this object. This is useful for testing and debugging. Sensitive data will be
     * redacted from this string using a placeholder value.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getData() != null)
            sb.append("Data: ").append(getData()).append(",");
        if (getNode() != null)
            sb.append("Node: ").append(getNode()).append(",");
        if (getNodetime() != null)
            sb.append("Nodetime: ").append(getNodetime()).append(",");
        if (getVersion() != null)
            sb.append("Version: ").append(getVersion());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof Answer == false)
            return false;
        Answer other = (Answer) obj;
        if (other.getData() == null ^ this.getData() == null)
            return false;
        if (other.getData() != null && other.getData().equals(this.getData()) == false)
            return false;
        if (other.getNode() == null ^ this.getNode() == null)
            return false;
        if (other.getNode() != null && other.getNode().equals(this.getNode()) == false)
            return false;
        if (other.getNodetime() == null ^ this.getNodetime() == null)
            return false;
        if (other.getNodetime() != null && other.getNodetime().equals(this.getNodetime()) == false)
            return false;
        if (other.getVersion() == null ^ this.getVersion() == null)
            return false;
        if (other.getVersion() != null && other.getVersion().equals(this.getVersion()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getData() == null) ? 0 : getData().hashCode());
        hashCode = prime * hashCode + ((getNode() == null) ? 0 : getNode().hashCode());
        hashCode = prime * hashCode + ((getNodetime() == null) ? 0 : getNodetime().hashCode());
        hashCode = prime * hashCode + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return hashCode;
    }

    @Override
    public Answer clone() {
        try {
            return (Answer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

    @com.amazonaws.annotation.SdkInternalApi
    @Override
    public void marshall(ProtocolMarshaller protocolMarshaller) {
        hu.arh.anprcloud.client.model.transform.AnswerMarshaller.getInstance().marshall(this, protocolMarshaller);
    }
}
