
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;

    public MaksukorttiTest() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void yhdenEdullisenHinnallaSaaVainYhdenEdullisen(){
        Maksukortti edullisenHinta = new Maksukortti(2.5);
        edullisenHinta.syoEdullisesti();
        edullisenHinta.syoEdullisesti();
        assertThat(edullisenHinta.toString(),is("Kortilla on rahaa 0.0 euroa"));
    }
    
    @Test
    public void yhdenMaukkaanHinnallaSaaVainYhdenMaukkaan(){
        Maksukortti maukkaanHinta = new Maksukortti(4.0);
        maukkaanHinta.syoMaukkaasti();
        maukkaanHinta.syoEdullisesti();
        assertThat(maukkaanHinta.toString(),is("Kortilla on rahaa 0.0 euroa"));
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivinenLatausEiMuutaSaldoa(){
        kortti.lataaRahaa(-200);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
}
