import { ref } from 'vue'
import { defineStore } from 'pinia'

const API_URL = 'http://localhost:8090/tasks'

export const useTasksStore = defineStore('tasks', () => {
    const tasks = ref([])

    async function fetchTasks() {
        try {
            const response = await fetch(API_URL);
            if (response.ok) {
                tasks.value = await response.json();
            }
        } catch (error) {
            console.error('Error fetching tasks:', error);
        }
    }

    async function updateStatus(id, newStatus) {
        try {
            const response = await fetch(`${API_URL}/${id}/status`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ status: newStatus.toUpperCase() })
            });
            if (response.ok) {
                const task = tasks.value.find((t) => t.id === id);
                if (task) task.status = newStatus;
            }
        } catch (error) {
            console.error('Error updating status:', error);
        }
    }

    async function addTask(taskData) {
        try {
            const payload = {
                ...taskData,
                dueDate: taskData.dueDate ? new Date(taskData.dueDate).toISOString() : null
            };
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            if (response.ok) {
                const newTask = await response.json();
                // Ensure the Java backend response is mapped properly if necessary
                if (newTask.status) newTask.status = newTask.status.toLowerCase();
                tasks.value.push(newTask);
            }
        } catch (error) {
            console.error('Error adding task:', error);
        }
    }

    async function deleteTask(id) {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                tasks.value = tasks.value.filter((t) => t.id !== id);
            }
        } catch (error) {
            console.error('Error deleting task:', error);
        }
    }

    async function clearCompleted() {
        try {
            const response = await fetch(`${API_URL}/completed/clear`, {
                method: 'DELETE'
            });
            if (response.ok) {
                tasks.value = tasks.value.filter((t) => t.status?.toLowerCase() !== 'done');
            }
        } catch (error) {
            console.error('Error clearing tasks:', error);
        }
    }

    return { tasks, fetchTasks, updateStatus, addTask, deleteTask, clearCompleted }
})
