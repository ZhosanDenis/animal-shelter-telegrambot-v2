package pro.sky.teamwork.animalsheltertelegrambotv2.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.teamwork.animalsheltertelegrambotv2.dto.AgreementRecord;
import pro.sky.teamwork.animalsheltertelegrambotv2.dto.CarerRecord;
import pro.sky.teamwork.animalsheltertelegrambotv2.dto.DogRecord;
import pro.sky.teamwork.animalsheltertelegrambotv2.model.Agreement;
import pro.sky.teamwork.animalsheltertelegrambotv2.model.Carer;
import pro.sky.teamwork.animalsheltertelegrambotv2.model.Dog;

import java.time.LocalDate;

@Service
public class ModelMapper {
    public Carer mapToCarerEntity(CarerRecord carerRecord) {
        Carer carer = new Carer();
        carer.setFullName(StringUtils.capitalize(carerRecord.getSecondName().toLowerCase()) + " " +
                StringUtils.capitalize(carerRecord.getFirstName().toLowerCase()) + " " +
                StringUtils.capitalize(carerRecord.getPatronymic().toLowerCase()));
        carer.setBirthYear(LocalDate.now().getYear() - carerRecord.getAge());
        carer.setPhoneNumber(carerRecord.getPhoneNumber());
        return carer;
    }

    public CarerRecord mapToCarerRecord(Carer carer) {
        String[] fullName = carer.getFullName().split(" ");
        CarerRecord carerRecord = new CarerRecord();
        carerRecord.setId(carer.getId());
        carerRecord.setSecondName(fullName[0]);
        carerRecord.setFirstName(fullName[1]);
        carerRecord.setPatronymic(fullName[2]);
        carerRecord.setAge(LocalDate.now().getYear() - carer.getBirthYear());
        carerRecord.setPhoneNumber(carer.getPhoneNumber());
        return carerRecord;
    }

    public Dog mapToDogEntity(DogRecord dogRecord) {
        Dog dog = new Dog();
        dog.setName(StringUtils.capitalize(dogRecord.getName().toLowerCase()));
        dog.setBreed(dogRecord.getBreed());
        dog.setCoatColor(dogRecord.getCoatColor());
        dog.setAge(dogRecord.getAge());
        dog.setFeatures(dogRecord.getFeatures());
        return dog;
    }

    public DogRecord mapToDogRecord(Dog dog) {
        DogRecord dogRecord = new DogRecord();
        dogRecord.setId(dog.getId());
        dogRecord.setName(dog.getName());
        dogRecord.setBreed(dog.getBreed());
        dogRecord.setCoatColor(dog.getCoatColor());
        dogRecord.setAge(dog.getAge());
        dogRecord.setFeatures(dog.getFeatures());
        return dogRecord;
    }

    public Agreement mapToAgreementEntity(AgreementRecord agreementRecord) {
        Agreement agreement = new Agreement();
//        agreement.setId(1);
        agreement.setNumber(agreementRecord.getNumber());
        agreement.setConclusionDate(agreementRecord.getConclusionDate());
        agreement.setCarer(agreementRecord.getCarer());
        return agreement;
    }

    public AgreementRecord mapToAgreementRecord(Agreement agreement) {
        AgreementRecord agreementRecord = new AgreementRecord();
        agreementRecord.setNumber(agreement.getNumber());
        agreementRecord.setConclusionDate(agreement.getConclusionDate());
        agreementRecord.setCarer(agreement.getCarer());
        return agreementRecord;
    }
}
