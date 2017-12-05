//: net/mindview/util/TypeCounter.java
// Counts instances of a type family.
package net.mindview.util;

import java.util.HashMap;

import typeinfo.pets.Cat;
import typeinfo.pets.Dog;
import typeinfo.pets.Pet;
import typeinfo.pets.Pug;

/**
 * @author xuechao
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(obj + " incorrect type: "
                + type + ", should be type or subtype of "
                + baseType);
        }
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        forEach((key, value) -> {
            result.append(key.getSimpleName());
            result.append("=");
            result.append(value);
            result.append(", ");
        });
        result.delete(result.length() - 2, result.length());
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        TypeCounter typeCounter = new TypeCounter(Pet.class);
        Pet[] pets = {new Cat(), new Dog(), new Pug()};
        for (Pet pet : pets) {
            typeCounter.count(pet);
        }
        System.out.println(typeCounter);
    }
} ///:~
