package synfron.reshaper.burp.ui.components.rules.thens;

import synfron.reshaper.burp.core.rules.thens.ThenDeleteVariable;
import synfron.reshaper.burp.core.vars.VariableSource;
import synfron.reshaper.burp.ui.models.rules.thens.ThenDeleteVariableModel;
import synfron.reshaper.burp.ui.utils.DocumentActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ThenDeleteVariableComponent extends ThenComponent<ThenDeleteVariableModel, ThenDeleteVariable> {
    private JComboBox<VariableSource> targetSource;
    private JTextField variableName;

    public ThenDeleteVariableComponent(ThenDeleteVariableModel then) {
        super(then);
        initComponent();
    }

    private void initComponent() {
        targetSource = new JComboBox<>(VariableSource.values());
        variableName = new JTextField();
        JButton save = new JButton("Save");

        targetSource.setSelectedItem(model.getTargetSource());
        variableName.setText(model.getVariableName());

        targetSource.addActionListener(this::onTargetSourceChanged);
        variableName.getDocument().addDocumentListener(new DocumentActionListener(this::onVariableNameChanged));
        save.addActionListener(this::onSave);

        mainContainer.add(getLabeledField("Variable Source", targetSource), "wrap");
        mainContainer.add(getLabeledField("Variable Name", variableName), "wrap");
        mainContainer.add(getPaddedButton(save));
    }

    private void onTargetSourceChanged(ActionEvent actionEvent) {
        model.setTargetSource((VariableSource) targetSource.getSelectedItem());
    }

    private void onVariableNameChanged(ActionEvent actionEvent) {
        model.setVariableName(variableName.getText());
    }
}
