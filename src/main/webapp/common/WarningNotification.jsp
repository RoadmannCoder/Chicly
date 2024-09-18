<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .notification {
        position: fixed;
        top: 0;
        width: 100%;
        background-color: #f44336; /* Red background for error */
        color: white;
        text-align: center;
        padding: 15px;
        z-index: 1000; /* Make sure it appears on top */
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 16px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        transition: transform 0.3s ease;
    }

    .notification.hidden {
        transform: translateY(-100%); /* Slide out of view */
    }

    .close-btn {
        margin-left: 20px;
        background: none;
        border: none;
        color: white;
        font-size: 18px;
        cursor: pointer;
    }
    /*end of Notification Style*/
</style>

<div id="notification" class="notification hidden">
    <span id="notification-message"></span>
    <button id="close-notification" class="close-btn">&times;</button>
</div>



<script>
    //Pass errorMessage to RequestScope its important
    var errorMessage = '<c:out value="${requestScope.errorMessage}" escapeXml="true" />';
    function showNotification(message) {
        const notification = document.getElementById('notification');
        const notificationMessage = document.getElementById('notification-message');

        // Set the message and show the notification
        notificationMessage.textContent = message;
        notification.classList.remove('hidden');

        // Automatically hide after 3 seconds
        setTimeout(() => {
            notification.classList.add('hidden');
        }, 5000);
    }
    document.addEventListener('DOMContentLoaded', function () {
        if (errorMessage) {
            showNotification(errorMessage);
        }
    });
</script>