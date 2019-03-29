package app.Entities;

public enum Crossover {
    type1 {
        void setType() {
            this.$type = "type1";
        }
    },

    type2 {
        void setType() {
            this.$type = "type2";
        }
    },

    type3 {
        void setType() {
            this.$type = "type3";
        }
    };

    public String $type;

    abstract void setType();

    Crossover() {
        this.setType();
    }
}
