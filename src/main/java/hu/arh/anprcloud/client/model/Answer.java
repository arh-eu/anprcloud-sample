/**
 * null
 */
package hu.arh.anprcloud.client.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.amazonaws.protocol.StructuredPojo;
import com.amazonaws.protocol.ProtocolMarshaller;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/ol6anslmcc-2020-05-04T08:02:27Z/Answer" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class Answer implements Serializable, Cloneable, StructuredPojo {

    private Data data;

    private String node;

    private Integer nodetime;

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
            sb.append("Nodetime: ").append(getNodetime());
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
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getData() == null) ? 0 : getData().hashCode());
        hashCode = prime * hashCode + ((getNode() == null) ? 0 : getNode().hashCode());
        hashCode = prime * hashCode + ((getNodetime() == null) ? 0 : getNodetime().hashCode());
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
