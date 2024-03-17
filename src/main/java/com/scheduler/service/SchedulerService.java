package com.scheduler.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scheduler.model.ClientNotificationConfig;
import com.scheduler.model.PartDefiningAttributeEntity;
import com.scheduler.model.PartDescriptiveAttributeEntity;
import com.scheduler.model.PartEntity;
import com.scheduler.model.ProductSeriesEntity;
import com.scheduler.model.SyndicationViewEntity;
import com.scheduler.repository.ClientNotificationConfigRepository;
import com.scheduler.repository.PartEntityRepository;
import com.scheduler.repository.ProductSeriesEntityRepository;
import com.scheduler.repository.SyndicationViewEntityRepository;

@Service
public class SchedulerService {

	@Autowired
	private ClientNotificationConfigRepository configRepository;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private ProductSeriesEntityRepository productSeriesEntityRepository;

	@Autowired
	private PartEntityRepository partEntityRepository;

	@Autowired
	private SyndicationViewEntityRepository syndicationViewEntityRepository;

	@Scheduled(cron = "0 59 23 * * *")
	public void sendDailylyNotifications() throws Exception {
		List<ClientNotificationConfig> configsWeekly = configRepository.findByFrequency("daily");

		sendCurrentDateEmail(configsWeekly);
	}

	private void sendCurrentDateEmail(List<ClientNotificationConfig> configsWeekly) throws Exception {
	
		 // Get the current date
        Calendar calendar = Calendar.getInstance();
        setZeroTime(calendar);
        // Convert Calendar to Date
        Date input = calendar.getTime();
	//	Date input = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		for (ClientNotificationConfig config : configsWeekly) {

			if (config.getModules().contains("marketing")) {
				List<PartEntity> listPartChanged = partEntityRepository.findByLastModifiedDateGreaterThan(input);

				List<ProductSeriesEntity> listProductSeriesChanged = productSeriesEntityRepository
						.findByLastModifiedDateGreaterThan(input);

				if ((listPartChanged != null || listPartChanged.isEmpty() == false) && listProductSeriesChanged != null
						|| listProductSeriesChanged.isEmpty() == false) {
					String emailBody = buildMarketingEmailBody(listPartChanged, listProductSeriesChanged);
					System.out.println(emailBody);

					emailService.sendEmail(config.getClientId(), emailBody, "Marketing Update");
				}

			}
			if (config.getModules().contains("technical")) {

				List<PartEntity> listPartChanged = partEntityRepository.findByLastModifiedDateGreaterThan(input);

				if (listPartChanged != null || listPartChanged.isEmpty() == false) {

					List<PartDefiningAttributeEntity> listPartDefiningAttributeEntity = new ArrayList<>();
					List<PartDescriptiveAttributeEntity> listPartDescriptiveAttributeEntity = new ArrayList<>();

					for (PartEntity partEntity : listPartChanged) {
						listPartDefiningAttributeEntity.addAll(partEntity.getDefiningAttributes());
						listPartDescriptiveAttributeEntity.addAll(partEntity.getDescriptiveAttributes());
					}

					String emailBody = buildTechnicalEmailBody(listPartDefiningAttributeEntity,
							listPartDescriptiveAttributeEntity);
					emailService.sendEmail(config.getClientId(), emailBody, "Technical Update");
				}
			}
			if (config.getModules().contains("logistics")) {
				List<SyndicationViewEntity> listSyndicationChanged = null;

				listSyndicationChanged = syndicationViewEntityRepository.findByLastUpdatedDateGreaterThan(input);
				if (listSyndicationChanged != null || listSyndicationChanged.isEmpty() == false) {

					String emailBody = buildLogisticEmailBody(listSyndicationChanged);
					System.out.println(emailBody);

					emailService.sendEmail(config.getClientId(), emailBody, "Logistics Update");
				}
			}

		}
	}

	@Scheduled(cron = "0 59 23 * * FRI") // Every Friday at midnight
	public void sendWeeklyNotifications() throws Exception {
		// List<ClientNotificationConfig> configs = configRepository.findAll();
		List<ClientNotificationConfig> configsWeekly = configRepository.findByFrequency("weekly");

		 // Get the current date
       Calendar calendar = Calendar.getInstance();
       // Set time to midnight
       setZeroTime(calendar);

		// Subtract 7 days from the current date
       Calendar sevenDaysOldDaye = Calendar.getInstance();
       sevenDaysOldDaye.add(Calendar.DAY_OF_MONTH, -7);
       setZeroTime(sevenDaysOldDaye);
       // Convert Calendar to Date
    
		sendEmailOnRange(configsWeekly, calendar.getTime(), sevenDaysOldDaye.getTime());
	}

	private void setZeroTime(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		   calendar.set(Calendar.MINUTE, 0);
		   calendar.set(Calendar.SECOND, 0);
		   calendar.set(Calendar.MILLISECOND, 0);
	}

	private void sendEmailOnRange(List<ClientNotificationConfig> configsWeekly, Date endDate,
			Date startDate) throws Exception {
	//	Date startDate = Date.from(dateSevenDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
	//	Date endDate = currentDate;

		for (ClientNotificationConfig config : configsWeekly) {

			if (config.getModules().contains("marketing")) {
				List<PartEntity> listPartChanged = partEntityRepository.findByLastModifiedDateBetween(startDate,
						endDate);

				List<ProductSeriesEntity> listProductSeriesChanged = productSeriesEntityRepository
						.findByLastModifiedDateBetween(startDate, endDate);
				if ((listPartChanged != null || listPartChanged.isEmpty() == false) && listProductSeriesChanged != null
						|| listProductSeriesChanged.isEmpty() == false) {

					String emailBody = buildMarketingEmailBody(listPartChanged, listProductSeriesChanged);
					System.out.println(emailBody);

					emailService.sendEmail(config.getClientId(), emailBody, "Marketing Update");
				}
			}
			if (config.getModules().contains("technical")) {

				List<PartEntity> listPartChanged = partEntityRepository.findByLastModifiedDateBetween(startDate,
						endDate);

				List<PartDefiningAttributeEntity> listPartDefiningAttributeEntity = new ArrayList<>();
				List<PartDescriptiveAttributeEntity> listPartDescriptiveAttributeEntity = new ArrayList<>();
				if (listPartChanged != null || listPartChanged.isEmpty() == false) {

					for (PartEntity partEntity : listPartChanged) {
						listPartDefiningAttributeEntity.addAll(partEntity.getDefiningAttributes());
						listPartDescriptiveAttributeEntity.addAll(partEntity.getDescriptiveAttributes());
					}

					String emailBody = buildTechnicalEmailBody(listPartDefiningAttributeEntity,
							listPartDescriptiveAttributeEntity);
					emailService.sendEmail(config.getClientId(), emailBody, "Technical Update");
				}
			}
			if (config.getModules().contains("logistics")) {
				List<SyndicationViewEntity> listSyndicationChanged = null;

				listSyndicationChanged = syndicationViewEntityRepository.findByLastUpdatedDateBetween(startDate,
						endDate);
				if (listSyndicationChanged != null || listSyndicationChanged.isEmpty() == false) {

					String emailBody = buildLogisticEmailBody(listSyndicationChanged);
					System.out.println(emailBody);

					emailService.sendEmail(config.getClientId(), emailBody, "Logistics Update");
				}
			}

		}
	}

	@Scheduled(cron = "0 59 23 * * *")
	public void sendCustomRangeNotifications() throws Exception {
		List<ClientNotificationConfig> configsWeekly = configRepository.findByFrequency("custom");
		for (ClientNotificationConfig config : configsWeekly) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date toDate = dateFormat.parse(config.getToDate());
			if (toDate.before(new Date())) {
				sendCurrentDateEmail(configsWeekly);
			}
		}
	}

	@Scheduled(cron = "0 59 23 * * *")
	public void sendMonthlyNotifications() throws Exception {
		
		  // Get the current date
        Calendar currentDate = Calendar.getInstance();
        setZeroTime(currentDate);
        // Get the last day of the current month
        Calendar lastDayOfMonth = (Calendar) currentDate.clone();
        lastDayOfMonth.set(Calendar.DAY_OF_MONTH, lastDayOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        setZeroTime(lastDayOfMonth);

        // Check if the current date is the last day of the month
        if (currentDate.get(Calendar.DAY_OF_MONTH) == lastDayOfMonth.get(Calendar.DAY_OF_MONTH)) {
            // Get first Day of Month
            Calendar firstDayOfMonth = (Calendar) currentDate.clone();
            firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
            
            setZeroTime(firstDayOfMonth);
            
            List<ClientNotificationConfig> configsWeekly = configRepository.findByFrequency("monthly");

			sendEmailOnRange(configsWeekly, firstDayOfMonth.getTime(), lastDayOfMonth.getTime());
			
        }

	}

	private String buildMarketingEmailBody(List<PartEntity> changedPartEntity,
			List<ProductSeriesEntity> changedProductSeriesEntity) {

		StringBuilder sb = new StringBuilder();
		sb.append(EmailBuilder.buildPartEmailBody(changedPartEntity));
		sb.append("<br/><br/>");
		sb.append(EmailBuilder.buildProductSeriesEmailBody(changedProductSeriesEntity));
		return sb.toString();
	}

	private String buildLogisticEmailBody(List<SyndicationViewEntity> changedItems) {
		// Implement logic to build email body in tabular format with item details
		StringBuilder sb = new StringBuilder();
		sb.append(EmailBuilder.buildSyndicationViewEmailBody(changedItems));

		return sb.toString();
	}

	private String buildTechnicalEmailBody(List<PartDefiningAttributeEntity> listPartDefiningAttributeEntity,
			List<PartDescriptiveAttributeEntity> listPartDescriptiveAttributeEntity) {
		StringBuilder sb = new StringBuilder();
		sb.append(EmailBuilder.buildPartDefiningAttributeEmailBody(listPartDefiningAttributeEntity));
		sb.append("<br/><br/>");
		sb.append(EmailBuilder.buildPartDescriptiveAttributeEmailBody(listPartDescriptiveAttributeEntity));
		return sb.toString();
	}

}
