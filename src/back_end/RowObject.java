package back_end;


import java.util.Date;

public class RowObject {

    public RowObject(RowObject rowValue) {
        super();
        this.date = rowValue.getDate();
        this.party_name = rowValue.getParty_name();
        this.vch_no =rowValue.getVch_no();
        this.gstn_uin = rowValue.getGstn_uin();
        this.city = rowValue.getCity();
        this.state = "Kerala";
        this.state_Code = "32";
        this.itemName = rowValue.getItemName();
        this.hsc_sac = rowValue.getHsc_sac();
        this.cess_p = rowValue.getCess_p();
        this.cess = rowValue.getCess();
        this.integrated_gst_p =rowValue.getIntegrated_gst_p();
        this.integrated_gst = rowValue.getIntegrated_gst_p();
        this.rate = rowValue.getRate();
        this.value = rowValue.getValue();
        this.quantity = rowValue.getQuantity();
        this.central_gst_p = rowValue.getCentral_gst_p();
        this.central_gst = rowValue.getCentral_gst();
        this.state_gst_p = rowValue.getState_gst_p();
        this.state_gst = rowValue.getState_gst();
        this.gross_Total = rowValue.getGross_Total();
    }
    Date date;
    String party_name;
    String vch_no;
    String gstn_uin;
    String city;
    String state="Kerala";
    String state_Code= "32";
    String itemName;
    String hsc_sac;
    double cess_p;
    double cess;
    double integrated_gst_p;
    double integrated_gst;
    double rate;
    double value;
    double quantity;
    double central_gst_p;
    double central_gst;
    double state_gst_p;
    double state_gst;
    double gross_Total;

    public Date getDate() {
        return date;
    }

    public String getParty_name() {
        return party_name;
    }
    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }
    public String getVch_no() {
        return vch_no;
    }
    public void setVch_no(String vch_no) {
        this.vch_no = vch_no;
    }
    public String getGstn_uin() {
        return gstn_uin;
    }
    public void setGstn_uin(String gstn_uin) {
        this.gstn_uin = gstn_uin;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getState_Code() {
        return state_Code;
    }
    public void setState_Code(String state_Code) {
        this.state_Code = state_Code;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getHsc_sac() {
        return hsc_sac;
    }
    public void setHsc_sac(String hsc_sac) {
        this.hsc_sac = hsc_sac;
    }
    public double getCess_p() {
        return cess_p;
    }
    public void setCess_p(double cess_p) {
        this.cess_p = cess_p;
    }
    public double getCess() {
        return cess;
    }
    public void setCess(double cess) {
        this.cess = cess;
    }
    public double getIntegrated_gst_p() {
        return integrated_gst_p;
    }
    public void setIntegrated_gst_p(double integrated_gst_p) {
        this.integrated_gst_p = integrated_gst_p;
    }
    public double getIntegrated_gst() {
        return integrated_gst;
    }
    public void setIntegrated_gst(double integrated_gst) {
        this.integrated_gst = integrated_gst;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public double getCentral_gst_p() {
        return central_gst_p;
    }
    public void setCentral_gst_p(double central_gst_p) {
        this.central_gst_p = central_gst_p;
    }
    public double getCentral_gst() {
        return central_gst;
    }
    public void setCentral_gst(double central_gst) {
        this.central_gst = central_gst;
    }
    public double getState_gst_p() {
        return state_gst_p;
    }
    public void setState_gst_p(double state_gst_p) {
        this.state_gst_p = state_gst_p;
    }
    public double getState_gst() {
        return state_gst;
    }
    public void setState_gst(double state_gst) {
        this.state_gst = state_gst;
    }
    public double getGross_Total() {
        return gross_Total;
    }
    public void setGross_Total(double gross_Total) {
        this.gross_Total = gross_Total;
    }
    @Override
    public String toString() {
        return "rowobject [date=" + date + ", party_name=" + party_name + ", vch_no=" + vch_no + ", gstn_uin="
                + gstn_uin + ", city=" + city + ", state=" + state + ", state_Code=" + state_Code + ", itemName="
                + itemName + ", hsc_sac=" + hsc_sac + ", cess_p=" + cess_p + ", cess=" + cess + ", integrated_gst_p="
                + integrated_gst_p + ", integrated_gst=" + integrated_gst + ", rate=" + rate + ", value=" + value
                + ", quantity=" + quantity + ", central_gst_p=" + central_gst_p + ", central_gst=" + central_gst
                + ", state_gst_p=" + state_gst_p + ", state_gst=" + state_gst + ", gross_Total=" + gross_Total + "]";
    }
    public RowObject() {
        super();
    }
    public void setDate(Date dateCellValue) {
        // TODO Auto-generated method stub
        this.date = dateCellValue;

    }






}
