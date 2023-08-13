package dev.seondsun.gsuoptimize;

public class GsuLine {

    public static GsuLine parse(String traceLine) {

        return null;
    }

    private Integer address;
    private String instruction;
    private String arguments;
    private int cycle;

    public static class Builder {

        private Integer address;
        private String instruction;
        private String arguments;
        private String cycle;

        public GsuLine build() {
            var x = new GsuLine();
            x.address = address;
            x.instruction = instruction;
            x.arguments = arguments;
            x.cycle = Integer.parseInt(cycle);
            return x;
        }

        public GsuLine.Builder setAddress(String string) {
            this.address = Integer.parseInt(string, 16);
            return this;
        }

        public GsuLine.Builder setInstruction(String string) {
            this.instruction = string;
            return this;
        }

        public GsuLine.Builder setArguments(String string) {
            this.arguments = string;
            return this;
        }

        public Builder setCycle(String string) {
            this.cycle = string;
            return this;
        }

    }

    public static Builder Builder() {
        return new Builder();
    }

    public int getAddress() {
        return address;
    }

    public String getInstruction() {
        return instruction;
    }

}
