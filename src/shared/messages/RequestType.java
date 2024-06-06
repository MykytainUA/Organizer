package shared.messages;

/**
 * Types of request
 */
public enum RequestType {
	
	UNKNOWN {
		@Override
		public String getAsString() {
			return "UNKNOWN";
		}
	},
	CHECK_CONNECTION {
		@Override
		public String getAsString() {
			return "CHECK_CONNECTION";
		}
	}, 
	SIGN_IN {
		@Override
		public String getAsString() {
			return "SIGN_IN";
		}
	},
	SIGN_UP {
		@Override
		public String getAsString() {
			return "SIGN_UP";
		}
	},
	
	ADD_NEW_TASK {
		@Override
		public String getAsString() {
			return "ADD_NEW_TASK";
		}
	},
	
	GET_ALL_TASKS_FOR_THIS_USER {
		@Override
		public String getAsString() {
			return "GET_ALL_TASKS_FOR_THIS_USER";
		}
	},
	
	MAKE_TASKS_COMPLETED {
		@Override
		public String getAsString() {
			return "MAKE_TASKS_COMPLETED";
		}
	},
	
	DELETE_TASKS {
		@Override
		public String getAsString() {
			return "DELETE_TASKS";
		}
	},
	
	MANAGE_MULTIPLE_TASKS {
		@Override
		public String getAsString() {
			return "MANAGE_MULTIPLE_TASKS";
		}
	};
	
	public String getAsString() {
		return "UNKNOWN";
	}
}
