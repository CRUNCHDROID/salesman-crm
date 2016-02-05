package br.com.kproj.salesman.notifications.application.subscribers;


import br.com.kproj.salesman.infrastructure.entity.notification.ScheduledTaskNotification;
import br.com.kproj.salesman.infrastructure.events.messages.NewTaskTriggerToExecuteMessage;
import br.com.kproj.salesman.notifications.application.NotificationApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTaskTriggerNotificationSubscriber {


    @Autowired
    private NotificationApplication application;


    @Subscribe
    public void generateNotificationBy(NewTaskTriggerToExecuteMessage message) {
        ScheduledTaskNotification notification = new ScheduledTaskNotification();
        notification.setCreateDate(message.getTriggerDate());
        notification.setTask(message.getTask());
        notification.setNotified(message.getUserNotified());

        application.senScheduledTaskdNotification(notification);
    }



}