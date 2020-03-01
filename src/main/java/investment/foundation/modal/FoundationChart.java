package investment.foundation.modal;

public class FoundationChart {
    private String code;
    private String name;
    private String actualDate;
    private String actualValue;
    private String actualGain;

    public FoundationChart(String code, String name, String actualDate, String actualValue, String actualGain) {
        this.code = code;
        this.name = name;
        this.actualDate = actualDate;
        this.actualValue = actualValue;
        this.actualGain = actualGain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualGain() {
        return actualGain;
    }

    public void setActualGain(String actualGain) {
        this.actualGain = actualGain;
    }
}
