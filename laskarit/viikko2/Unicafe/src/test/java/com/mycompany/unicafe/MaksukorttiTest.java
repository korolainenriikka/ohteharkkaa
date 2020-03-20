package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(100);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein(){
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoKasvaaOikein(){
        kortti.lataaRahaa(100);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoVäheneeOikeinKunRahaaOnTarpeeksi(){
        kortti.otaRahaa(50);
        assertEquals(50, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoVäheneeOikeinKunRahaaEiOleTarpeeksi(){
        kortti.otaRahaa(200);
        assertEquals(100, kortti.saldo());
    }
  
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittää(){
        assertTrue(kortti.otaRahaa(50));
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahatEiRiitä(){
        assertFalse(kortti.otaRahaa(200));
    }
    
    @Test
    public void toStringTulostaaOikein(){
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
}
