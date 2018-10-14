package Models;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
    private int status;

    public Response(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
