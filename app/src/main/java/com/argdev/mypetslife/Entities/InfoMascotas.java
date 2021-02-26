package com.argdev.mypetslife.Entities;

public class InfoMascotas implements Comparable<InfoMascotas> {

    String Species;
    String SpeciesUrl;
    int SpeciesPosition;

    public InfoMascotas(){

    }

    public InfoMascotas(String species, String speciesUrl, int speciesPosition) {
        Species = species;
        SpeciesUrl = speciesUrl;
        SpeciesPosition = speciesPosition;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getSpeciesUrl() {
        return SpeciesUrl;
    }

    public void setSpeciesUrl(String speciesUrl) {
        SpeciesUrl = speciesUrl;
    }

    public int getSpeciesPosition() {
        return SpeciesPosition;
    }

    public void setSpeciesPosition(int speciesPosition) {
        SpeciesPosition = speciesPosition;
    }

    @Override
    public int compareTo(InfoMascotas o) {

        if(o.getSpeciesPosition() > SpeciesPosition){
            return -1;
        }
        else return 1;
    }
}
