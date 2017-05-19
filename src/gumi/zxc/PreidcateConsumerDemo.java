package gumi.zxc;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


/**
 * @author gumi.zxc
 * @since 2017/5/19 17:56
 */

class Student {
    private String fristName;

    private String lastName;

    public Double grade;

    private Double feeDiscount = 0.0;

    private Double baseFee = 20000.0;

    public Student(String fristName, String lastName, Double grade) {
        this.fristName = fristName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public void printFee() {
        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
        System.out.println("The fee after discount: " + newFee);
    }

    @Override
    public String toString() {
        return MessageFormat.format("firstName:{0} lastName:{1} grade:{2}", this.fristName, this.lastName, this.grade);
    }

    public void print() {
        toString();
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getFeeDiscount() {
        return feeDiscount;
    }

    public void setFeeDiscount(Double feeDiscount) {
        this.feeDiscount = feeDiscount;
    }

    public Double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Double baseFee) {
        this.baseFee = baseFee;
    }
}


public class PreidcateConsumerDemo implements Predicate<Student> {
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer) {
        if(predicate.test(student)) {
            consumer.accept(student);
        }
        return student;
    }

    public static boolean testGrade(Student student) {
        return student.getGrade() > 8.0;
    }

    @Override
    public boolean test(Student student) {
        return student.getGrade() > 8.0? true: false;
    }

    public static void main(String[] args) {
        Student student1 = new Student("Ashok","Kumar", 9.5);

        Student student2 = new Student("Frank","Xiong", 7.5);

        //student1 = updateStudentFee(student1,student -> student.getGrade() > 8.5, student -> {student.setFeeDiscount(0.9);});

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);

        list.stream().filter(PreidcateConsumerDemo::testGrade).forEach(System.out::println);


    }
}
