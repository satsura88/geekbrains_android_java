package ru.geekbrains.hw3;

public enum CalcOperation {

    EMPTY{
        @Override
        public String toString() {
            return "";
        }
    },
    PLUS{
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACTION{
        @Override
        public String toString() {
            return "-";
        }
    },
    DIVISION{
        @Override
        public String toString() {
            return "÷";
        }
    },
    MULTIPLICATION{
        @Override
        public String toString() {
            return "x";
        }
    }
}
