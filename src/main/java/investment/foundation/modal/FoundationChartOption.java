package investment.foundation.modal;

import java.util.List;
import java.util.Objects;

public class FoundationChartOption {
    private String code;
    private String name;
    private List<String> xAxis;
    private List<String> yAxis;

    public FoundationChartOption(String code, String name, List<String> xAxis, List<String> yAxis) {
        this.code = code;
        this.name = name;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
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

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<String> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<String> yAxis) {
        this.yAxis = yAxis;
    }

    @Override
    public String toString() {
        return "FoundationChartOption{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoundationChartOption that = (FoundationChartOption) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(xAxis, that.xAxis) &&
                Objects.equals(yAxis, that.yAxis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, xAxis, yAxis);
    }
}
