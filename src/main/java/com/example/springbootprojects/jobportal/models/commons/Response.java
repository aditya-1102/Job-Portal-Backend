package com.example.springbootprojects.jobportal.models.commons;

public class Response {

    private Object data;

    public Response(){
        super();
    }
    public Response(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                '}';
    }
}
