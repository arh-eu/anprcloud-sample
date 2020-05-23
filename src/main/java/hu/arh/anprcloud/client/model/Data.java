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
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/ol6anslmcc-2020-05-04T08:02:27Z/Data" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class Data implements Serializable, Cloneable, StructuredPojo {

    private Mmr mmr;

    private java.util.List<PlatesElement> plates;

    /**
     * @param mmr
     */

    public void setMmr(Mmr mmr) {
        this.mmr = mmr;
    }

    /**
     * @return
     */

    public Mmr getMmr() {
        return this.mmr;
    }

    /**
     * @param mmr
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Data mmr(Mmr mmr) {
        setMmr(mmr);
        return this;
    }

    /**
     * @return
     */

    public java.util.List<PlatesElement> getPlates() {
        return plates;
    }

    /**
     * @param plates
     */

    public void setPlates(java.util.Collection<PlatesElement> plates) {
        if (plates == null) {
            this.plates = null;
            return;
        }

        this.plates = new java.util.ArrayList<PlatesElement>(plates);
    }

    /**
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if any). Use
     * {@link #setPlates(java.util.Collection)} or {@link #withPlates(java.util.Collection)} if you want to override the
     * existing values.
     * </p>
     * 
     * @param plates
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Data plates(PlatesElement... plates) {
        if (this.plates == null) {
            setPlates(new java.util.ArrayList<PlatesElement>(plates.length));
        }
        for (PlatesElement ele : plates) {
            this.plates.add(ele);
        }
        return this;
    }

    /**
     * @param plates
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public Data plates(java.util.Collection<PlatesElement> plates) {
        setPlates(plates);
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
        if (getMmr() != null)
            sb.append("Mmr: ").append(getMmr()).append(",");
        if (getPlates() != null)
            sb.append("Plates: ").append(getPlates());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof Data == false)
            return false;
        Data other = (Data) obj;
        if (other.getMmr() == null ^ this.getMmr() == null)
            return false;
        if (other.getMmr() != null && other.getMmr().equals(this.getMmr()) == false)
            return false;
        if (other.getPlates() == null ^ this.getPlates() == null)
            return false;
        if (other.getPlates() != null && other.getPlates().equals(this.getPlates()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getMmr() == null) ? 0 : getMmr().hashCode());
        hashCode = prime * hashCode + ((getPlates() == null) ? 0 : getPlates().hashCode());
        return hashCode;
    }

    @Override
    public Data clone() {
        try {
            return (Data) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

    @com.amazonaws.annotation.SdkInternalApi
    @Override
    public void marshall(ProtocolMarshaller protocolMarshaller) {
        hu.arh.anprcloud.client.model.transform.DataMarshaller.getInstance().marshall(this, protocolMarshaller);
    }
}
