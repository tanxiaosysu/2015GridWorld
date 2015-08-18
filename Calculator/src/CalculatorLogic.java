public class CalculatorLogic {
    private Double attr1;
    private Double attr2;
    private Double result;
    private String currentType;

    public void setCurrentType(String inputType) {
        currentType = inputType;
    }

    public void setAttr1(Double attr) {
        attr1 = attr;
    }

    public void setAttr2(Double attr) {
        attr2 = attr;
    }

    public Double getResult() {
        return result;
    }

    public void calculate() {
        switch (currentType) {
        case "add":
            result = attr1 + attr2;
            break;
        case "dec":
            result = attr1 - attr2;
            break;
        case "mul":
            result = attr1 * attr2;
            break;
        case "div":
            result = attr1 / attr2;
            break;
        default:
            break;
        }
    }
}
