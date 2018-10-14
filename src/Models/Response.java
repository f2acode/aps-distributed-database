package Models;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
    private Status status;

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
