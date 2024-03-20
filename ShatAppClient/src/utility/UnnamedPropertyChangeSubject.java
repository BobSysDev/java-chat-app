package utility;

import java.beans.PropertyChangeListener;

public interface UnnamedPropertyChangeSubject
{

    public void addListener(PropertyChangeListener listener);
    public void removeListener(PropertyChangeListener listener);

}
