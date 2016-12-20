package ru.unn.agile.vector3d.infrastructure;

import ru.unn.agile.vector3d.viewmodel.AbstractLogger;
import ru.unn.agile.vector3d.viewmodel.ViewModel;
import ru.unn.agile.vector3d.viewmodel.ViewModelTest;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ViewModelWithFileLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        FileLogger logger =
                new FileLogger("./vector3dViewModelLogger.log");
        super.setViewModel(new ViewModel(logger));
    }

    @Override
    protected void findRequiredText(final AbstractLogger logger, final String requiredText) {
        boolean textFound = false;

        Iterator<String> it = logger.iterator();
        while (it.hasNext()) {
            if (matchesPattern(".*" + requiredText + "$", it.next()).find()) {
                textFound = true;
                break;
            }
        }

        assertTrue(textFound);
    }

    private Matcher matchesPattern(final String regexp, final String search) {
        Pattern pattern = Pattern.compile(regexp);
        return pattern.matcher(search);
    }
}
