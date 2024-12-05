package com.codex.modal;

import com.codex.domain.VerificationType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class TwoFactorAuth {
	private boolean isEnabled = false;
	private VerificationType sendTo;
	public boolean isEnabled() {
		return isEnabled;
	}
	public VerificationType getSendTo() {
		return sendTo;
	}
	public void setSendTo(VerificationType sendTo) {
		this.sendTo = sendTo;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
