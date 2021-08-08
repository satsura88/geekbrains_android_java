package ru.geekbrains.hw3;

import java.io.Serializable;

public class Calculator implements Serializable {

    private double memNumber;
    private double curNumber;
    private CalcOperation operation;
    private String resultField;
    private String operationField;

    public Calculator() {
        this.memNumber = 0.0;
        this.curNumber = 0.0;
        this.operation = CalcOperation.EMPTY;
        this.resultField= "";
        this.operationField= "";
    }

    public String getResultField(){
        return this.resultField;
    }

    public String getOperationField(){
        return this.operationField;
    }

    public void setCurNumber(String number){
        if (number.equals(""))
            this.curNumber = 0.0;
        else
            this.curNumber = Double.valueOf(number);

        this.operationField = number;
    }

    public void setInMem(String number, CalcOperation oper){
        this.memNumber = Double.valueOf(number);
        this.curNumber = 0.0;
        this.operation = oper;

        this.resultField = memNumber + oper.toString();
        this.operationField = "";
    }

    public CalcOperation getOperation(){
        return this.operation;
    }

    public void calc(Double number){
        switch (this.operation){
            case PLUS:
                this.memNumber = this.memNumber + Double.valueOf(number);
                break;
            case SUBTRACTION:
                this.memNumber = this.memNumber - Double.valueOf(number);
                break;
            case DIVISION:
                this.memNumber = this.memNumber / Double.valueOf(number);
                break;
            case MULTIPLICATION:
                this.memNumber = this.memNumber * Double.valueOf(number);
                break;
        }

        this.operation = CalcOperation.EMPTY;
        this.curNumber=this.memNumber;

        this.operationField=String.valueOf(curNumber);
        this.resultField = this.resultField+number+"="+memNumber;
    }
}
