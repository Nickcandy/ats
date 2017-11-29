package com.hpe.itsma.bo.ats.sample;

import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.hpe.itsma.bo.common.utils.UtilMock.*;

/**
 * Created by yhuang1 on 10/2/2017.
 */
public class SampleDataGenerator {
    private static final int[] CUSTOMER_IDS = {1001, 1002, 1003, 1004, 1005};
    private static final int[] ACCOUNT_IDS = {100000020, 100000021, 100000022, 100000023, 100000024, 100000025, 100000026, 100000027};
    private static final int[] TENANT_IDS = {100000030, 100000031, 100000022, 100000033};
    private static final int[] CONTACT_IDS = {9901, 9902, 9903, 9904, 9905, 9906, 9907, 9908, 9909, 9910, 9911, 9912, 9913, 9914};

    private static final String SAMPLE_DATA_PATH = "C:\\studio\\bo\\bo-account-tenant\\src\\main\\resources\\data";

    private static Logger logger = LoggerFactory.getLogger(SampleDataGenerator.class);

    private static String genSampleCustomerCSV(int numOfRec) throws IOException {
        StringBuilder ret = new StringBuilder("id,full_name,short_name,status,contact,email,phone,description\n");
        for (int i = 0; i < numOfRec; i++) {
            ret.append(String.valueOf(CUSTOMER_IDS[i % CUSTOMER_IDS.length]));
            ret.append(",");
            ret.append(randomName(3));
            ret.append(",");
            ret.append(randomName(2));
            ret.append(",");
            ret.append(CustomerStatus.ACTIVE);
            ret.append(",");
            ret.append(randomName(2));
            ret.append(",");
            ret.append(randomEmail());
            ret.append(",");
            ret.append(randomPhoneNumber());
            ret.append(",");
            ret.append(randomDescription());
            ret.append("\n");
        }
        return writeOotpFile("Customer.csv", ret);
    }

    private static String writeOotpFile(String fileName, StringBuilder fileBuffer) throws IOException {
        File outputFile = new File(SAMPLE_DATA_PATH, fileName);
        String content = null;
        try (
                Writer writer = new FileWriter(outputFile);
                BufferedWriter out = new BufferedWriter(writer);
        ){
            content = fileBuffer.toString();
            out.write(content);
        }catch (IOException e) {
            logger.info(e.getMessage());
        }
        logger.info(fileName);
        logger.info(content);
        return content;
    }

    private static String genSampleAccountCSV(int numOfRec) throws IOException {
        StringBuilder ret = new StringBuilder("id,name,customer,description,account_status,account_type,account_region,country,state,city\n");
        for (int i = 0; i < numOfRec; i++) {
            ret.append(String.valueOf(ACCOUNT_IDS[i % ACCOUNT_IDS.length]));
            ret.append(",");
            ret.append(randomName(3));
            ret.append(",");
            ret.append(CUSTOMER_IDS[i % CUSTOMER_IDS.length]);
            ret.append(",");
            ret.append(randomDescription());
            ret.append(",");
            ret.append(AccountStatus.ACTIVE);
            ret.append(",");
            ret.append((AccountType.values())[i % (AccountType.values().length)].toString());
            ret.append(",");
            ret.append((AccountRegion.values())[i % (AccountRegion.values().length)].toString());
            ret.append(",");
            ret.append("China");//country
            ret.append(",");
            ret.append("Shanghai");//state
            ret.append(",");
            ret.append("Shanghai");//city
            ret.append("\n");
        }
        return writeOotpFile("Account.csv", ret);
    }

    private static String genSampleTenantCSV(int numOfRec) throws IOException {
        StringBuilder ret = new StringBuilder("id,name,description,tenant_type,account_id,tenant_state,is_public\n");
        for (int i = 0; i < numOfRec; i++) {
            ret.append(String.valueOf(TENANT_IDS[i % TENANT_IDS.length]));
            ret.append(",");
            ret.append(randomName(3));
            ret.append(",");
            ret.append(randomDescription());
            ret.append(",");
            ret.append(TenantType.DEV);
            ret.append(",");
            ret.append(ACCOUNT_IDS[i % ACCOUNT_IDS.length]);
            ret.append(",");
            ret.append(TenantState.NEW);
            ret.append(",");
            ret.append(Boolean.TRUE);
            ret.append("\n");
        }
        return writeOotpFile("Tenant.csv", ret);
    }

    private static String genSampleContactCSV(int numOfRec) throws IOException {
        StringBuilder ret = new StringBuilder("id,name,description,full_name,email,account_id,role,timezone,region,phone,address\n");
        for (int i = 0; i < numOfRec; i++) {
            ret.append(String.valueOf(CONTACT_IDS[i % CONTACT_IDS.length]));
            ret.append(",");
            ret.append(randomName(3));
            ret.append(",");
            ret.append(randomDescription());
            ret.append(",");
            ret.append(randomName(3));
            ret.append(",");
            ret.append(randomEmail());
            ret.append(",");
            ret.append(ACCOUNT_IDS[i % ACCOUNT_IDS.length]);
            ret.append(",");
            ret.append((ContactRole.values())[i % (ContactRole.values().length)].toString());
            ret.append(",");
            ret.append("Africa/Abidjan");
            ret.append(",");
            ret.append((AccountRegion.values())[i % (AccountRegion.values().length)].toString());
            ret.append(",");
            ret.append(randomPhoneNumber());
            ret.append(",");
            ret.append(randomDescription());
            ret.append("\n");
        }
        return writeOotpFile("Contact.csv", ret);
    }

    public static void main(String[] args) throws Exception {
        genSampleCustomerCSV(CUSTOMER_IDS.length);
        genSampleAccountCSV(ACCOUNT_IDS.length);
        genSampleTenantCSV(TENANT_IDS.length);
        genSampleContactCSV(CONTACT_IDS.length);
    }
}
