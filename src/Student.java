import java.util.Scanner;

public class Student
{
    private String name;
    private int age;
    private String id;
    private int phoneNumber;

    public Student() {
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        if(age < 0){
            age = 0;
        } else if(age > 100){
            age = 100;
        }
        this.age = age;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void inputInfo()
    {
        Scanner student = new Scanner(System.in);
        System.out.println("Enter Student Name: ");
        name = student.nextLine();
        System.out.println("Enter Student Age: ");
        age = student.nextInt();
        student.nextLine();
        System.out.println("Enter Student ID: ");
        id = student.nextLine();
        System.out.println("Enter Student Phone Number: ");
        phoneNumber = student.nextInt();

    }
    public void outputInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + id);
        System.out.println("Phone Number: " + phoneNumber);
    }
}

