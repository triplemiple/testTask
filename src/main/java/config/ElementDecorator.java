package config;

import elements.AbstractElement;
import elements.interfaces.ElementInt;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Decorator for custom elements.
 */
public class ElementDecorator extends DefaultFieldDecorator {

    public ElementDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (ElementInt.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        return super.decorate(loader, field);
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        return createInstance(field.getType(), wrappedElement);
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

    private <T extends AbstractElement> T createInstance(Class<?> klass, WebElement element) {
        try {
            return (T) klass.getConstructor(WebElement.class).newInstance(element);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
