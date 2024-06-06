package shared.messages;

/**
 * Types of response
 */
public enum ResponseState {
	
	UNKNOWN {
		@Override
		public String getAsString() {
			return "UNKNOWN";
		}
	},
	
	SUCCESS {
		@Override
		public String getAsString() {
			return "SUCCESS";
		}
	}, 
	
	NO_SUCH_ENTRY_IN_DATABASE {
		@Override
		public String getAsString() {
			return "NO_SUCH_ENTRY_IN_DATABASE";
		}
	}, 
	
	LOGIN_AND_PASSWORD_ARE_TOO_SMALL {
		@Override
		public String getAsString() {
			return "LOGIN_AND_PASSWORD_ARE_TOO_SMALL";
		}
	}, 
	
	SUCH_LOGIN_ALREADY_EXIST {
		@Override
		public String getAsString() {
			return "SUCH_LOGIN_ALREADY_EXIST";
		}
	},
	
	DATE_ALREADY_OCCUPIED {
		@Override
		public String getAsString() {
			return "DATE_ALREADY_OCCUPIED";
		}
	},
	
	NO_TASKS_WERE_DELETED {
		@Override
		public String getAsString() {
			return "NO_TASKS_WERE_DELETED";
		}
	},
	
	NO_TASKS_WERE_CHANGED {
		@Override
		public String getAsString() {
			return "NO_TASKS_WERE_CHANGED";
		}
	},
	
	ERROR {
		@Override
		public String getAsString() {
			return "ERROR";
		}
	}; 
	
	public String getAsString() {
		return "UNKNOWN";
	}
}
