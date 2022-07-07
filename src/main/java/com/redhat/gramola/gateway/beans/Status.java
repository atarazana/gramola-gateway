
package com.redhat.gramola.gateway.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for Status
 * <p>
 * The root of the Status type's schema.
 * 
 * 
 * {
 *     "status": "UP",
 *     "checks": [
 *         {
 *             "name": "Database connections health check",
 *             "status": "UP"
 *         }
 *     ]
 * }
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "checks"
})
public class Status {

    @JsonProperty("status")
    private String status;

    @JsonProperty("checks")
    private Check[] checks = new Check[0];

    public Status() {
    }
    
    public Status(String status, Check[] checks) {
        this.status = status;
        this.checks = checks;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("checks")
    public Check[] getChecks() {
        return checks;
    }

    @JsonProperty("checks")
    public void setChecks(Check[] checks) {
        this.checks = checks;
    }
}
