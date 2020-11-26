package synfron.reshaper.burp.ui.models.rules.whens;

import lombok.Getter;
import synfron.reshaper.burp.core.messages.MessageValue;
import synfron.reshaper.burp.core.rules.whens.WhenHasEntity;
import synfron.reshaper.burp.core.vars.VariableString;
import synfron.reshaper.burp.ui.models.rules.RuleOperationModelType;

public class WhenHasEntityModel extends WhenModel<WhenHasEntityModel, WhenHasEntity> {

    @Getter
    private MessageValue messageValue;
    @Getter
    private String identifier = "";

    public WhenHasEntityModel(WhenHasEntity when, Boolean isNew) {
        super(when, isNew);
        messageValue = when.getMessageValue();
        identifier = VariableString.getFormattedString(when.getIdentifier(), identifier);
    }

    public void setMessageValue(MessageValue messageValue) {
        this.messageValue = messageValue;
        propertyChanged("messageValue", messageValue);
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        propertyChanged("identifier", identifier);
    }

    public boolean persist() {
        if (validate().size() != 0) {
            return false;
        }
        ruleOperation.setMessageValue(messageValue);
        ruleOperation.setIdentifier(VariableString.getAsVariableString(identifier));
        return super.persist();
    }

    @Override
    public boolean record() {
        if (validate().size() != 0) {
            return false;
        }
        setSaved(true);
        return true;
    }

    @Override
    public RuleOperationModelType<WhenHasEntityModel, WhenHasEntity> getType() {
        return WhenModelType.HasEntity;
    }
}
