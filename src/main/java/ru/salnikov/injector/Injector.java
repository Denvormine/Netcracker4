package ru.salnikov.injector;

import ru.salnikov.annotations.ForInjection;
import ru.salnikov.exceptions.InjectorException;
import ru.salnikov.fileworker.FileManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class Injector {

    public static void inject(Object obj) throws InjectorException {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            Map<Class, Class> classMap = FileManager.LoadInjectionFromFile("C:\\Users\\Denvormine\\IdeaProjects\\task_1\\src\\main\\resources\\injector.cfg");
            for (Field field : fields) {
                if (field.getAnnotation(ForInjection.class) != null) {
                    field.setAccessible(true);
                    Class impl = classMap.get(field.getType());
                    //System.out.println(impl);
                    if (impl == null) {
                        throw new Exception("No implementation for " + field.getAnnotatedType().toString());
                    }
                    field.set(obj, impl.getConstructor().newInstance());
                }
            }
        } catch (Exception exception) {
            throw new InjectorException(exception);
        }

    }
}
