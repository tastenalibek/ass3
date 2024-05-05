import java.util.Objects;

public class MyTestingClass {
    private String data;

    public MyTestingClass(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public int hashCode() {
        int h = 11;
        for (int i = 0; i < data.length(); i++) {
            h = h * 13 + data.charAt(i);
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return Objects.equals(data, other.data);
    }
}
