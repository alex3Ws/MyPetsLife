package com.argdev.mypetslife.Entities;

public class InfoMascotas implements Comparable<InfoMascotas> {

    String SpeciesBreed;
    String SpeciesBreedUrl;
    int SpeciesBreedPosition;

    public InfoMascotas(){

    }

    public InfoMascotas(String species, String speciesUrl, int speciesPosition) {
        SpeciesBreed = species;
        SpeciesBreedUrl = speciesUrl;
        SpeciesBreedPosition = speciesPosition;
    }

    public String getSpeciesBreed() {
        return SpeciesBreed;
    }

    public void setSpeciesBreed(String species) {
        SpeciesBreed = species;
    }

    public String getSpeciesBreedUrl() {
        return SpeciesBreedUrl;
    }

    public void setSpeciesUrl(String speciesUrl) {
        SpeciesBreedUrl = speciesUrl;
    }

    public int getSpeciesBreedPosition() {
        return SpeciesBreedPosition;
    }

    public void setSpeciesPosition(int speciesPosition) {
        SpeciesBreedPosition = speciesPosition;
    }

    @Override
    public int compareTo(InfoMascotas o) {

        if(o.getSpeciesBreedPosition() > SpeciesBreedPosition){
            return -1;
        }
        else return 1;
    }
}
