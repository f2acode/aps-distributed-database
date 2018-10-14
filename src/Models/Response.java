package Models;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
    private int status;

    public Response(int status) {
        this.status = status;
    }

    public int getValidez() {
        return status;
    }

    public void setValidez(int status) {
        this.status = status;
    }
}
