/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author mauro
 */
public class Menus {
    private Boolean menu1;
    private Boolean menu2;
    private Boolean menu3;
    private Boolean menu4;
    private Boolean menu5;

    public Menus(Boolean menu1, Boolean menu2, Boolean menu3, Boolean menu4,Boolean menu5) {
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.menu4 = menu4;
        this.menu5=menu5;
    }

    public Boolean getMenu5() {
        return menu5;
    }

    public Boolean getMenu1() {
        return menu1;
    }

    public Boolean getMenu2() {
        return menu2;
    }

    public Boolean getMenu3() {
        return menu3;
    }

    public Boolean getMenu4() {
        return menu4;
    }
    
    
}
