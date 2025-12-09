/* ============================================
   SYSTEM INITIALIZATION & DATA MANAGEMENT
   ============================================ */

class StudentManager {
    constructor() {
        this.students = this.loadData();
        this.editingStudentId = null;
        this.editingScoreSubject = null;
        this.deleteCallback = null;
    }

    // Load data from localStorage
    loadData() {
        const data = localStorage.getItem('students');
        return data ? JSON.parse(data) : [];
    }

    // Save data to localStorage
    saveData() {
        localStorage.setItem('students', JSON.stringify(this.students));
        this.updateDashboard();
    }

    // Add new student
    addStudent(id, name, className, birthYear) {
        if (this.students.find(s => s.id === id)) {
            alert('Mã học sinh đã tồn tại!');
            return false;
        }

        const student = {
            id: id,
            name: name,
            class: className,
            birthYear: birthYear,
            subjects: []
        };

        this.students.push(student);
        this.saveData();
        return true;
    }

    // Edit student
    editStudent(id, name, className, birthYear) {
        const student = this.students.find(s => s.id === id);
        if (student) {
            student.name = name;
            student.class = className;
            student.birthYear = birthYear;
            this.saveData();
            return true;
        }
        return false;
    }

    // Delete student
    deleteStudent(id) {
        this.students = this.students.filter(s => s.id !== id);
        this.saveData();
    }

    // Get student by ID
    getStudent(id) {
        return this.students.find(s => s.id === id);
    }

    // Add subject score for a student
    addSubjectScore(studentId, subject, regularScores, midtermScore, finalScore) {
        const student = this.getStudent(studentId);
        if (!student) return false;

        // Check if subject already exists
        let subjectData = student.subjects.find(s => s.name === subject);
        if (!subjectData) {
            subjectData = {
                name: subject,
                regularScores: [],
                midtermScore: 0,
                finalScore: 0
            };
            student.subjects.push(subjectData);
        }

        // Update scores
        subjectData.regularScores = regularScores;
        subjectData.midtermScore = parseFloat(midtermScore);
        subjectData.finalScore = parseFloat(finalScore);

        this.saveData();
        return true;
    }

    // Calculate DTB Mon (Subject Average)
    calculateDTBMon(subjectData) {
        if (!subjectData || !subjectData.regularScores) return 0;

        const regularSum = subjectData.regularScores.reduce((a, b) => a + b, 0);
        const regularCount = subjectData.regularScores.length;
        const totalWeight = regularCount * 1 + 2 + 3; // TX weight 1, GK weight 2, CK weight 3

        if (totalWeight === 0) return 0;

        const dtb = (regularSum * 1 + subjectData.midtermScore * 2 + subjectData.finalScore * 3) / totalWeight;
        return Math.round(dtb * 100) / 100;
    }

    // Calculate DTBC (General Average)
    calculateDTBC(studentId) {
        const student = this.getStudent(studentId);
        if (!student || student.subjects.length === 0) return 0;

        let totalDTB = 0;
        student.subjects.forEach(subject => {
            totalDTB += this.calculateDTBMon(subject);
        });

        const dtbc = totalDTB / student.subjects.length;
        return Math.round(dtbc * 100) / 100;
    }

    // Classify student
    classifyStudent(dtbc) {
        if (dtbc >= 9.0) return 'Xuất Sắc';
        if (dtbc >= 8.0) return 'Giỏi';
        if (dtbc >= 6.5) return 'Khá';
        if (dtbc >= 5.0) return 'Trung Bình';
        return 'Yếu';
    }

    // Get all students with DTBC
    getAllStudentsWithDTBC() {
        return this.students.map(student => ({
            ...student,
            dtbc: this.calculateDTBC(student.id),
            classification: this.classifyStudent(this.calculateDTBC(student.id))
        })).sort((a, b) => b.dtbc - a.dtbc);
    }

    // Get top students
    getTopStudents(limit = 5) {
        return this.getAllStudentsWithDTBC().slice(0, limit);
    }

    // Get statistics
    getStatistics() {
        const allStudents = this.getAllStudentsWithDTBC();
        return {
            total: allStudents.length,
            excellent: allStudents.filter(s => s.classification === 'Xuất Sắc').length,
            good: allStudents.filter(s => s.classification === 'Giỏi').length,
            average: allStudents.filter(s => s.classification === 'Khá').length,
            fair: allStudents.filter(s => s.classification === 'Trung Bình').length,
            weak: allStudents.filter(s => s.classification === 'Yếu').length
        };
    }

    // Get subject statistics
    getSubjectStatistics() {
        const subjects = {};

        this.students.forEach(student => {
            student.subjects.forEach(subject => {
                if (!subjects[subject.name]) {
                    subjects[subject.name] = {
                        count: 0,
                        scores: []
                    };
                }
                subjects[subject.name].count++;
                subjects[subject.name].scores.push(this.calculateDTBMon(subject));
            });
        });

        const stats = [];
        for (const [name, data] of Object.entries(subjects)) {
            const scores = data.scores;
            stats.push({
                name: name,
                count: data.count,
                max: Math.max(...scores),
                min: Math.min(...scores),
                average: Math.round((scores.reduce((a, b) => a + b, 0) / scores.length) * 100) / 100
            });
        }

        return stats;
    }

    // Clear all data
    clearAll() {
        this.students = [];
        localStorage.removeItem('students');
    }

    // Export data as JSON
    exportData() {
        return JSON.stringify(this.students, null, 2);
    }

    // Import data from JSON
    importData(jsonString) {
        try {
            const data = JSON.parse(jsonString);
            if (Array.isArray(data)) {
                this.students = data;
                this.saveData();
                return true;
            }
            return false;
        } catch (error) {
            console.error('Import error:', error);
            return false;
        }
    }
}

/* ============================================
   DOM MANIPULATION & EVENT HANDLERS
   ============================================ */

const manager = new StudentManager();

// Update date and time
function updateDateTime() {
    const now = new Date();
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
    document.getElementById('dateTime').textContent = now.toLocaleDateString('vi-VN', options);
}

// Tab switching
document.querySelectorAll('.tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const tabName = btn.getAttribute('data-tab');

        // Hide all tabs
        document.querySelectorAll('.tab-content').forEach(tab => {
            tab.classList.remove('active');
        });

        // Remove active class from all buttons
        document.querySelectorAll('.tab-btn').forEach(b => {
            b.classList.remove('active');
        });

        // Show selected tab
        document.getElementById(tabName).classList.add('active');
        btn.classList.add('active');

        // Refresh data when switching to certain tabs
        if (tabName === 'dashboard') {
            updateDashboard();
        } else if (tabName === 'reports') {
            updateReports();
        }
    });
});

/* ============================================
   DASHBOARD FUNCTIONS
   ============================================ */

function updateDashboard() {
    const stats = manager.getStatistics();
    const topStudents = manager.getTopStudents(5);

    // Update stat cards
    document.getElementById('totalStudents').textContent = stats.total;
    document.getElementById('excellent').textContent = stats.excellent;
    document.getElementById('good').textContent = stats.good;
    document.getElementById('average').textContent = stats.average;

    // Update top students table
    const tbody = document.getElementById('topStudentsBody');
    if (topStudents.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="text-center">Chưa có dữ liệu</td></tr>';
    } else {
        tbody.innerHTML = topStudents.map((student, index) => `
            <tr>
                <td>${index + 1}</td>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.class}</td>
                <td><strong>${student.dtbc}</strong></td>
                <td><span class="badge badge-${student.classification.toLowerCase()}">${student.classification}</span></td>
            </tr>
        `).join('');
    }
}

document.getElementById('refreshDashboard').addEventListener('click', updateDashboard);

/* ============================================
   STUDENTS MANAGEMENT FUNCTIONS
   ============================================ */

function loadStudentsList() {
    const tbody = document.getElementById('studentsTableBody');
    const select = document.getElementById('searchStudent');

    if (manager.students.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="text-center">Chưa có học sinh</td></tr>';
        select.innerHTML = '<option value="">-- Chọn học sinh --</option>';
        return;
    }

    // Update table
    tbody.innerHTML = manager.students.map(student => `
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.class}</td>
            <td>${student.birthYear}</td>
            <td>${student.subjects.length}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-primary btn-small" onclick="editStudent('${student.id}')">
                        <i class="fas fa-edit"></i> Sửa
                    </button>
                    <button class="btn btn-danger btn-small" onclick="deleteStudentConfirm('${student.id}', '${student.name}')">
                        <i class="fas fa-trash"></i> Xóa
                    </button>
                </div>
            </td>
        </tr>
    `).join('');

    // Update select dropdown
    select.innerHTML = '<option value="">-- Chọn học sinh --</option>' +
        manager.students.map(student => `<option value="${student.id}">${student.id} - ${student.name}</option>`).join('');
}

document.getElementById('addStudentBtn').addEventListener('click', () => {
    manager.editingStudentId = null;
    document.getElementById('formTitle').textContent = 'Thêm Học Sinh Mới';
    document.getElementById('studentForm').reset();
    document.getElementById('studentFormContainer').style.display = 'block';
});

document.getElementById('cancelStudentForm').addEventListener('click', () => {
    document.getElementById('studentFormContainer').style.display = 'none';
    manager.editingStudentId = null;
});

document.getElementById('studentForm').addEventListener('submit', (e) => {
    e.preventDefault();

    const id = document.getElementById('studentId').value;
    const name = document.getElementById('studentName').value;
    const className = document.getElementById('studentClass').value;
    const birthYear = document.getElementById('studentBirthYear').value;

    if (manager.editingStudentId) {
        manager.editStudent(manager.editingStudentId, name, className, birthYear);
    } else {
        manager.addStudent(id, name, className, birthYear);
    }

    document.getElementById('studentFormContainer').style.display = 'none';
    loadStudentsList();
    document.getElementById('studentForm').reset();
    manager.editingStudentId = null;
});

function editStudent(studentId) {
    const student = manager.getStudent(studentId);
    if (!student) return;

    manager.editingStudentId = studentId;
    document.getElementById('formTitle').textContent = 'Chỉnh Sửa Học Sinh';
    document.getElementById('studentId').value = student.id;
    document.getElementById('studentId').disabled = true;
    document.getElementById('studentName').value = student.name;
    document.getElementById('studentClass').value = student.class;
    document.getElementById('studentBirthYear').value = student.birthYear;

    document.getElementById('studentFormContainer').style.display = 'block';
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function deleteStudentConfirm(studentId, studentName) {
    const modal = document.getElementById('deleteModal');
    document.getElementById('deleteMessage').textContent = `Bạn có chắc muốn xóa học sinh "${studentName}"?`;

    document.getElementById('confirmDelete').onclick = () => {
        manager.deleteStudent(studentId);
        loadStudentsList();
        modal.classList.remove('show');
        updateDashboard();
    };

    document.getElementById('cancelDelete').onclick = () => {
        modal.classList.remove('show');
    };

    modal.classList.add('show');
}

/* ============================================
   SCORES MANAGEMENT FUNCTIONS
   ============================================ */

let regularScoresArray = [];

document.getElementById('searchStudent').addEventListener('change', (e) => {
    const studentId = e.target.value;
    loadScoresTable(studentId);
    if (studentId) {
        document.getElementById('scoreFormContainer').style.display = 'block';
    } else {
        document.getElementById('scoreFormContainer').style.display = 'none';
    }
});

document.getElementById('addRegularScore').addEventListener('click', () => {
    const scoreInput = document.getElementById('scoreRegular');
    const score = parseFloat(scoreInput.value);

    if (isNaN(score) || score < 0 || score > 10) {
        alert('Vui lòng nhập điểm từ 0 đến 10');
        return;
    }

    regularScoresArray.push(score);
    displayRegularScores();
    scoreInput.value = '';
});

function displayRegularScores() {
    const container = document.getElementById('regularScoresList');
    container.innerHTML = regularScoresArray.map((score, index) => `
        <div class="score-badge">
            ${score}
            <button type="button" onclick="removeRegularScore(${index})">×</button>
        </div>
    `).join('');
}

function removeRegularScore(index) {
    regularScoresArray.splice(index, 1);
    displayRegularScores();
}

document.getElementById('scoreForm').addEventListener('submit', (e) => {
    e.preventDefault();

    const studentId = document.getElementById('searchStudent').value;
    const subject = document.getElementById('subjectName').value;
    const midterm = parseFloat(document.getElementById('scoreMidterm').value);
    const final = parseFloat(document.getElementById('scoreFinal').value);

    if (regularScoresArray.length === 0) {
        alert('Vui lòng thêm ít nhất một điểm thường xuyên');
        return;
    }

    manager.addSubjectScore(studentId, subject, regularScoresArray, midterm, final);
    loadScoresTable(studentId);

    // Reset form
    document.getElementById('scoreForm').reset();
    regularScoresArray = [];
    displayRegularScores();
});

document.getElementById('cancelScoreForm').addEventListener('click', () => {
    document.getElementById('scoreFormContainer').style.display = 'none';
});

function loadScoresTable(studentId) {
    const student = manager.getStudent(studentId);
    const tbody = document.getElementById('scoresTableBody');

    if (!student || student.subjects.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="text-center">Chưa có điểm môn</td></tr>';
        return;
    }

    tbody.innerHTML = student.subjects.map(subject => {
        const dtb = manager.calculateDTBMon(subject);
        const regularScoresStr = subject.regularScores.join(', ');

        return `
            <tr>
                <td><strong>${subject.name}</strong></td>
                <td>${regularScoresStr}</td>
                <td>${subject.midtermScore}</td>
                <td>${subject.finalScore}</td>
                <td><strong>${dtb}</strong></td>
                <td>
                    <div class="action-buttons">
                        <button class="btn btn-primary btn-small" onclick="editScore('${studentId}', '${subject.name}')">
                            <i class="fas fa-edit"></i> Sửa
                        </button>
                        <button class="btn btn-danger btn-small" onclick="deleteScore('${studentId}', '${subject.name}')">
                            <i class="fas fa-trash"></i> Xóa
                        </button>
                    </div>
                </td>
            </tr>
        `;
    }).join('');
}

function editScore(studentId, subjectName) {
    const student = manager.getStudent(studentId);
    const subject = student.subjects.find(s => s.name === subjectName);

    if (!subject) return;

    manager.editingScoreSubject = subjectName;
    document.getElementById('subjectName').value = subjectName;
    document.getElementById('scoreMidterm').value = subject.midtermScore;
    document.getElementById('scoreFinal').value = subject.finalScore;
    regularScoresArray = [...subject.regularScores];
    displayRegularScores();

    document.getElementById('scoreFormContainer').style.display = 'block';
}

function deleteScore(studentId, subjectName) {
    const student = manager.getStudent(studentId);
    student.subjects = student.subjects.filter(s => s.name !== subjectName);
    manager.saveData();
    loadScoresTable(studentId);
}

/* ============================================
   REPORTS FUNCTIONS
   ============================================ */

function updateReports() {
    updateClassificationStats();
    updateRankings();
    updateSubjectStats();
}

function updateClassificationStats() {
    const stats = manager.getStatistics();
    const total = stats.total;

    if (total === 0) {
        ['excellent', 'good', 'average', 'fair', 'weak'].forEach(type => {
            document.getElementById(`${type}Bar`).style.width = '0%';
            document.getElementById(`${type}Count`).textContent = '0/0';
        });
        return;
    }

    const counts = [
        { id: 'excellent', count: stats.excellent },
        { id: 'good', count: stats.good },
        { id: 'average', count: stats.average },
        { id: 'fair', count: stats.fair },
        { id: 'weak', count: stats.weak }
    ];

    counts.forEach(item => {
        const percentage = (item.count / total) * 100;
        document.getElementById(`${item.id}Bar`).style.width = `${percentage}%`;
        document.getElementById(`${item.id}Count`).textContent = `${item.count}/${total}`;
    });
}

function updateRankings() {
    const allStudents = manager.getAllStudentsWithDTBC();
    const tbody = document.getElementById('rankingsBody');

    if (allStudents.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" class="text-center">Chưa có dữ liệu</td></tr>';
        return;
    }

    tbody.innerHTML = allStudents.map((student, index) => `
        <tr>
            <td>${index + 1}</td>
            <td>${student.name}</td>
            <td><strong>${student.dtbc}</strong></td>
            <td>
                <span class="badge badge-${student.classification.toLowerCase()}">
                    ${student.classification}
                </span>
            </td>
        </tr>
    `).join('');
}

function updateSubjectStats() {
    const subjectStats = manager.getSubjectStatistics();
    const tbody = document.getElementById('subjectsBody');

    if (subjectStats.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" class="text-center">Chưa có dữ liệu</td></tr>';
        return;
    }

    tbody.innerHTML = subjectStats.map(subject => `
        <tr>
            <td>${subject.name}</td>
            <td>${subject.count}</td>
            <td>${subject.max}</td>
            <td>${subject.min}</td>
            <td>${subject.average}</td>
        </tr>
    `).join('');
}

/* ============================================
   SETTINGS FUNCTIONS
   ============================================ */

document.getElementById('exportDataBtn').addEventListener('click', () => {
    const data = manager.exportData();
    const blob = new Blob([data], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `student-data-${new Date().getTime()}.json`;
    a.click();
    URL.revokeObjectURL(url);
});

document.getElementById('importDataBtn').addEventListener('click', () => {
    document.getElementById('importFile').click();
});

document.getElementById('importFile').addEventListener('change', (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (event) => {
        try {
            if (manager.importData(event.target.result)) {
                alert('Dữ liệu đã được nhập thành công!');
                loadStudentsList();
                updateDashboard();
            } else {
                alert('Lỗi: Định dạng file không hợp lệ!');
            }
        } catch (error) {
            alert('Lỗi khi nhập dữ liệu: ' + error.message);
        }
    };
    reader.readAsText(file);
});

document.getElementById('clearDataBtn').addEventListener('click', () => {
    if (confirm('Bạn có chắc chắn muốn xóa tất cả dữ liệu? Hành động này không thể hoàn tác!')) {
        if (confirm('Vui lòng xác nhận lần nữa:')) {
            manager.clearAll();
            alert('Tất cả dữ liệu đã được xóa!');
            loadStudentsList();
            updateDashboard();
        }
    }
});

/* ============================================
   MODAL FUNCTIONS
   ============================================ */

document.getElementById('closeDetails').addEventListener('click', () => {
    document.getElementById('detailsModal').classList.remove('show');
});

window.addEventListener('click', (e) => {
    const deleteModal = document.getElementById('deleteModal');
    const detailsModal = document.getElementById('detailsModal');

    if (e.target === deleteModal) {
        deleteModal.classList.remove('show');
    }
    if (e.target === detailsModal) {
        detailsModal.classList.remove('show');
    }
});

/* ============================================
   INITIALIZATION
   ============================================ */

document.addEventListener('DOMContentLoaded', () => {
    updateDateTime();
    setInterval(updateDateTime, 60000); // Update time every minute

    loadStudentsList();
    updateDashboard();

    // Load sample data if needed
    if (manager.students.length === 0) {
        // Add some sample data for demo
        loadSampleData();
    }
});

function loadSampleData() {
    // Sample students
    const sampleData = [
        {
            id: "HS001",
            name: "Phạm Công Vinh",
            class: "9A1",
            birthYear: 2009,
            subjects: [
                { name: "Toán", regularScores: [8.5, 8.0, 8.5], midtermScore: 8.0, finalScore: 9.0 },
                { name: "Ngữ Văn", regularScores: [7.5, 7.0, 7.5], midtermScore: 7.5, finalScore: 8.0 },
                { name: "Tiếng Anh", regularScores: [8.0, 8.5, 8.0], midtermScore: 8.5, finalScore: 8.5 }
            ]
        },
        {
            id: "HS002",
            name: "Hoàng Mạnh Quân",
            class: "9A1",
            birthYear: 2009,
            subjects: [
                { name: "Toán", regularScores: [9.0, 9.5, 9.0], midtermScore: 9.0, finalScore: 9.5 },
                { name: "Ngữ Văn", regularScores: [8.5, 8.5, 8.0], midtermScore: 8.5, finalScore: 9.0 },
                { name: "Tiếng Anh", regularScores: [9.5, 9.0, 9.5], midtermScore: 9.0, finalScore: 9.5 }
            ]
        }
    ];

    // Remove comment below to load sample data automatically
    // manager.students = sampleData;
    // manager.saveData();
    // loadStudentsList();
    // updateDashboard();
}
