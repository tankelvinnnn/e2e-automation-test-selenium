package com.selenium.scripts.payment;

import java.sql.SQLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.selenium.common.DatabaseConnection;
import com.selenium.helpers.payment.PaymentPrivateHelper;

public class PaymentPrivateTest extends PaymentPrivateHelper{
    @Test(priority = 1)
    public void successPaymentForCakapPrivate() throws Throwable {
        try {
            //TestEngine.testDescription.put(HtmlReportSupport.tc_name, "TC-01: Login Using Valid Account");
            accessPrivateCampaign();
            selectPackage();
            createStudentAccount();
            waitForMilliseconds(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @AfterTest
    // public void clearData() throws SQLException{
    //     DatabaseConnection.openConnection();
    //     DatabaseConnection.executeQuery("INSERT INTO `squline-dashboard`.student_delete_reason (student_id, delete_information_master_id, other_reason, platform, created_date, created_by) SELECT id_user, 6, 'QA Testing Account', 'web', now(), 'kelvin@cakap.com' FROM `squline-dashboard`.tbl_user WHERE user_email='kelvin@cakap.com';");
    //     DatabaseConnection.executeQuery("DELETE FROM `squline-dashboard`.oauth_access_token WHERE user_name = 'kelvin@cakap.com';");
    //     DatabaseConnection.executeQuery("UPDATE `squline-dashboard`.tbl_user SET user_email = CONCAT(SUBSTR(user_email, 0, 1), SUBSTR(uuid(), 1, length(user_email))), facebook_id = CONCAT(SUBSTR(facebook_id, 0, 1), SUBSTR(uuid(), 1, length(facebook_id))),google_id = CONCAT(SUBSTR(google_id, 0, 1), SUBSTR(uuid(), 1, length(google_id))),apple_id = CONCAT(SUBSTR(apple_id, 0, 1), SUBSTR(uuid(), 1, length(apple_id))),user_password = CONCAT(SUBSTR(user_password, 0, 1), SUBSTR(uuid(), 1, length(user_password))),user_phone = CONCAT(SUBSTR(user_phone, 0, 1), SUBSTR(uuid(), 1, length(user_phone))),user_city = CONCAT(SUBSTR(user_city, 0, 1), SUBSTR(uuid(), 1, length(user_city))),email_google_calendar = CONCAT(SUBSTR(email_google_calendar, 0, 1), SUBSTR(uuid(), 1, length(email_google_calendar))),user_fullname = 'DELETED ACCOUNT',user_firstname = 'DELETED',user_lastname = 'ACCOUNT',user_picture = 'no_image_genderless.png',last_ip = '0.0.0.0',ip_address = '0.0.0.0',activation_code = 'deleted',user_status = 'banned',book_status = 'disable',mobile_notification = 'null',user_source_database = null,campaign = null,has_sync_google_calendar = null,store_name = null,referral_link = null,referral = null,modified_date = NOW()WHERE user_email = 'kelvin@cakap.com';");
    //     DatabaseConnection.executeQuery("UPDATE `squline-dashboard`.tbl_agent_prospect_student_leads SET email = (SELECT tbl_user.user_email FROM `squline-dashboard`.tbl_user WHERE tbl_user.id_user = tbl_agent_prospect_student_leads.id_user),phone = (SELECT `squline-dashboard`.tbl_user.user_phone FROM `squline-dashboard`.tbl_user WHERE tbl_user.id_user = tbl_agent_prospect_student_leads.id_user),prospect_name = CONCAT(prospect_name, '-', 'DELETED_ACCOUNT') WHERE email = 'kelvin@cakap.com';");
    //     DatabaseConnection.closeConnection();
    // }

    @AfterTest
    public void clearDataWithAPI() throws Throwable {
        deleteAccount();
    }
}
