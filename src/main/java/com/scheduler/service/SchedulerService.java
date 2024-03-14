package com.scheduler.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scheduler.model.ClientNotificationConfig;
import com.scheduler.model.PartDefiningAttributeEntity;
import com.scheduler.model.PartDescriptiveAttributeEntity;
import com.scheduler.model.PartEntity;
import com.scheduler.model.Product;
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
		LocalDate currentDate = LocalDate.now();
		currentDate=currentDate.minusDays(1);
		// Convert LocalDate to Date
		Date input = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

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
		LocalDate currentDate = LocalDate.now();

		// Subtract 7 days from the current date
		LocalDate dateSevenDaysAgo = currentDate.minusDays(7);

		// Convert LocalDate to Date
		sendEmailOnRange(configsWeekly, currentDate, dateSevenDaysAgo);
	}

	private void sendEmailOnRange(List<ClientNotificationConfig> configsWeekly, LocalDate currentDate,
			LocalDate dateSevenDaysAgo) throws Exception {
		Date startDate = Date.from(dateSevenDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

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
		// Determine the current date
		LocalDate currentDate = LocalDate.now();

		// Get the last day of the current month
		LocalDate lastDayOfMonth = YearMonth.from(currentDate).atEndOfMonth();

		// Check if the current date is the last day of the month
		if (currentDate.getDayOfMonth() == lastDayOfMonth.getDayOfMonth()) {
			// Get first Day of Month
			LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

			List<ClientNotificationConfig> configsWeekly = configRepository.findByFrequency("monthly");

			sendEmailOnRange(configsWeekly, firstDayOfMonth, lastDayOfMonth);

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
