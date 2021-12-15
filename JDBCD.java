package week6;
import java.sql.*;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCD {
    public static void main(String[] args) {
        StudentSql sql = new StudentSql();
        System.out.println("------------------第一题-------------------");
        Student stu1 = new Student("s001", "老大", 20, "计算机学院");
        Student stu2 = new Student("s002", "老二", 19, "计算机学院");
        Student stu3 = new Student("s003", "老三", 18, "计算机学院");
        Student stu4 = new Student("s004", "老四", 17, "计算机学院");
        sql.insertStudent(stu1);
        sql.insertStudent(stu2);
        sql.insertStudent(stu3);
        sql.insertStudent(stu4);
        sql.selectAllStudent();
        System.out.println("------------------第二题-------------------");
        sql.selectAllStudent();
        System.out.println("-----------------第三题--------------------");
        sql.deleteStuBySno("s004");
        sql.selectAllStudent();
        System.out.println("-----------------第四题--------------------");
        Student tmp = sql.selectStudentBySno("s003");
        System.out.println(tmp);
        sql.selectAllStudent();
        System.out.println("-----------------第五题--------------------");
        stu1.setCollege("通信学院");
        sql.updateCollegeByStu(stu1);
        sql.selectAllStudent();

    }
}

//连接到MySQL类
class JDBC{
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/student?characterEncoding=utf8";
    public static final String USER = "root";
    public static final String PASSWORD = "278169";
    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection get_Connection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
    public static void get_CloseConnection(ResultSet rs, PreparedStatement pstm, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
class StudentSql {
    Connection conn = null;//连接
    PreparedStatement pstm = null;//语句框架
    ResultSet rs = null;//结果
    //插入语句
    public void insertStudent(Student stu) {
        try {
            //建立与MySQL的连接
            conn = JDBC.get_Connection();
            //搭建框架
            pstm = conn.prepareStatement("insert into student(sno, name, age, college) VALUES (?,?,?,?)");
            //插入对应数值
            pstm.setString(1, stu.getSno());
            pstm.setString(2, stu.getName());
            pstm.setInt(3, stu.getAge());
            pstm.setString(4, stu.getCollege());
            int i = pstm.executeUpdate();
            if (i >= 0) {
                System.out.println("保存成功...");
            }
        } catch (SQLException e) {
            System.out.println("保存失败...");
            e.printStackTrace();
        } finally {
            try {
                //关闭连接
                JDBC.get_CloseConnection(rs, pstm, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Student selectStudentBySno(String sno) {
        try {
            Student stu = new Student();
            conn = JDBC.get_Connection();
            pstm = conn.prepareStatement("select * from student where sno=?");
            pstm.setString(1, sno);
            rs = pstm.executeQuery();
            while (rs.next()){
                stu.setSno(rs.getString("sno"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setCollege(rs.getString("college"));
                return stu;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBC.get_CloseConnection(rs, pstm, conn);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //更新语句
    public void updateCollegeByStu(Student stu) {
        try {
            conn = JDBC.get_Connection();
            pstm = conn.prepareStatement("update student set college=? where sno=?");
            pstm.setString(1, stu.getCollege());
            pstm.setString(2, stu.getSno());
            int i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBC.get_CloseConnection(rs, pstm, conn);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //删除语句
    public void deleteStuBySno(String sno) {
        try {
            conn = JDBC.get_Connection();
            pstm = conn.prepareStatement("delete from student where sno=?");
            pstm.setString(1, sno);
            int i = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBC.get_CloseConnection(rs, pstm, conn);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //查询语句(查询全部)
    public void selectAllStudent() {
        try {
            conn = JDBC.get_Connection();
            pstm = conn.prepareStatement("select * from student");
            //executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
            rs = pstm.executeQuery();
            while(rs.next()) {
                System.out.println(
                        rs.getString(1) + "," +
                                rs.getString(2) + "," +
                                rs.getInt(3) + "," +
                                rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBC.get_CloseConnection(rs, pstm, conn);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
class Student {
    private String sno;
    private String name;
    private int age;
    private  String college;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Student(String sno, String name, int age, String college) {
        this.sno = sno;
        this.name = name;
        this.age = age;
        this.college = college;
    }

    public Student () {
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", college='" + college + '\'' +
                '}';
    }
}