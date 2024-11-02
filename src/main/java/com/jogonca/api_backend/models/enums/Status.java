package com.jogonca.api_backend.models.enums;

public enum Status {

    WAITING(1),
	COMPLETED_AND_SUCESSFUL(2),
	CANCELED(3);

    private final int status;

    private Status(int i) {
        this.status = i;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        return switch(status){
            case 1 -> "aguardando";
            case 2 -> "concluido";
            case 3 -> "cancelado";
            default -> throw new IllegalArgumentException("Unexpected value: " + status);
        };
    }

    public static Status valueOf(int code) {
		for(Status value : Status.values()) {
			if(value.getStatus() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Status code");
	}
    
}

