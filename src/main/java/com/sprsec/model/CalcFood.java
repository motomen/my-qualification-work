package com.sprsec.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 05.03.2015.
 */

@Entity
@Table(name = "calc_food")
public class CalcFood implements Comparable{

    @Id
    @Column(name = "id_calc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCalc;

    @Column(name = "add_date")
    private Date addDate;

    private double value;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    // Remember to remove the fetchType attribute from the @*ToMany annotation.
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "calc_to_food",
            joinColumns = {@JoinColumn(name="fk_calc", referencedColumnName = "id_calc")},
            inverseJoinColumns = {@JoinColumn(name="fk_food", referencedColumnName = "id_food_tc")}

    )
    private Food food;

    public int getIdCalc() {
        return idCalc;
    }

    public void setIdCalc(int idCalc) {
        this.idCalc = idCalc;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalcFood that = (CalcFood) o;

        if (user.getIdUser() != that.user.getIdUser()) return false;
        if (idCalc != that.idCalc) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (addDate != null ? !addDate.equals(that.addDate) : that.addDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idCalc;
        result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + user.getIdUser();
        return result;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        if (this.idCalc == ((CalcFood) o).idCalc) {
            return 0;
        }
        if (this.addDate.compareTo(((CalcFood) o).addDate) > 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
