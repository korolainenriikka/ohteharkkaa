package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    private Kassapaate kassapaate;

    @Before
    public void SetUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void kassapaateOnOlemassa() {
        assertTrue(kassapaate != null);
    }

    @Test
    public void kassapaatteessOnOikeaSaldo() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassapaatteessaLounaitaOnOikeaMaara() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty() + kassapaate.maukkaitaLounaitaMyyty());
    }

    //käteismaksu
    @Test
    public void kateisotoRiittavaKassanRahamaaraKasvaaLounaanHinnalla() {
        kassapaate.syoEdullisesti(300);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void kateisotoRiittavaVaihtorahanSuuruusOikea() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }

    @Test
    public void kateisostoEiRiitaKassanRahamaaraEiMuutu() {
        kassapaate.syoEdullisesti(1);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kateisostoEiRiitaKaikkiRahatPalautetaan() {
        assertEquals(1, kassapaate.syoEdullisesti(1));
    }

    @Test
    public void kateisostoEiRiitaLounaidenMaaraEiMuutu() {
        kassapaate.syoMaukkaasti(1);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    //korttimaksu
    @Test
    public void kortillaTarpeeksiRahaaSummaVeloitetaan(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void kortillaTarpeeksiRahaaPalautetaanTrue(){
        Maksukortti kortti = new Maksukortti(1000);     
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaTarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutu(){
        Maksukortti kortti = new Maksukortti(1);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kortti.saldo());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaPalautetaanFalse(){
        Maksukortti kortti = new Maksukortti(1);
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaMyytyjenLounaidenMaaraEiMuutu(){
        Maksukortti kortti = new Maksukortti(1);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        
    }
      
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    
    //rahan lataus kortille
    
    @Test
    public void kortinRahamaaraKasvaaOikein(){
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void negatiivinenLatausEiKasvataSaldoa(){
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(0, kortti.saldo());
    }
    
    

}
