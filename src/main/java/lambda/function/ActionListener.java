package lambda.function;

import java.awt.event.ActionEvent;
import java.util.EventListener;

@SuppressWarnings("ALL")
@FunctionalInterface
public interface ActionListener extends EventListener {
    public void actionPerformed(ActionEvent e);
}
