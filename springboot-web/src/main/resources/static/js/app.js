// API Base URL
const API_BASE = '/api';

// State
let students = [];
let scores = [];
let currentStudentId = null;

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    setupTabs();
    loadStudents();
    loadScores();
    setupEventListeners();
});

// Tab Navigation
function setupTabs() {
    const navLinks = document.querySelectorAll('.nav-link');
    const tabContents = document.querySelectorAll('.tab-content');

    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const tabName = link.getAttribute('data-tab');

            // Remove active class
            navLinks.forEach(l => l.classList.remove('active'));
            tabContents.forEach(t => t.classList.remove('active'));

            // Add active class
            link.classList.add('active');
            document.getElementById(`${tabName}-tab`).classList.add('active');

            // Load data for specific tabs
            if (tabName === 'reports') {
                loadRankings();
                loadSubjectStats();
            } else if (tabName === 'dashboard') {
                loadDashboard();
            }
        });
    });

    // Load dashboard on startup
    loadDashboard();
}

// Event Listeners
function setupEventListeners() {
    // Student form
    document.getElementById('student-form').addEventListener('submit', (e) => {
        e.preventDefault();
        addStudent();
    });

    // Score form
    document.getElementById('score-form').addEventListener('submit', (e) => {
        e.preventDefault();
        addScore();
    });
}

// ========== DASHBOARD ==========
function loadDashboard() {
    fetchAPI(`${API_BASE}/hoc-sinh/stats/summary`, 'GET').then(data => {
        const totalStudents = students.length;
        document.getElementById('total-students').textContent = totalStudents;

        // Count by classification
        const xatSac = students.filter(s => s.xepLoai === 'Xuất Sắc').length;
        const gioi = students.filter(s => s.xepLoai === 'Giỏi').length;
        const kha = students.filter(s => s.xepLoai === 'Khá').length;

        document.getElementById('xat-sac').textContent = xatSac;
        document.getElementById('gioi').textContent = gioi;
        document.getElementById('kha').textContent = kha;

        loadTopStudents();
    });
}

function loadTopStudents() {
    const topStudents = students
        .filter(s => s.diemTrungBinh && s.diemTrungBinh > 0)
        .sort((a, b) => b.diemTrungBinh - a.diemTrungBinh)
        .slice(0, 5);

    const tbody = document.getElementById('top-students-body');
    tbody.innerHTML = '';

    topStudents.forEach((student, index) => {
        tbody.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${student.maHS}</td>
                <td>${student.tenHS}</td>
                <td>${student.lop}</td>
                <td><strong>${(student.diemTrungBinh || 0).toFixed(2)}</strong></td>
                <td><span class="badge">${student.xepLoai || 'Chưa xếp loại'}</span></td>
            </tr>
        `;
    });

    if (topStudents.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" style="text-align: center; color: #999;">Chưa có dữ liệu</td></tr>';
    }
}

// ========== STUDENTS ==========
function loadStudents() {
    fetchAPI(`${API_BASE}/hoc-sinh`, 'GET').then(data => {
        students = data;
        updateStudentTable();
        updateStudentSelect();
    });
}

function updateStudentTable() {
    const tbody = document.getElementById('students-body');
    tbody.innerHTML = '';

    students.forEach(student => {
        tbody.innerHTML += `
            <tr>
                <td>${student.maHS}</td>
                <td>${student.tenHS}</td>
                <td>${student.lop}</td>
                <td>${student.namSinh}</td>
                <td><strong>${(student.diemTrungBinh || 0).toFixed(2)}</strong></td>
                <td><span class="badge">${student.xepLoai || 'Chưa xếp loại'}</span></td>
                <td>
                    <div class="table-actions">
                        <button class="btn btn-edit" onclick="editStudent(${student.id})">Sửa</button>
                        <button class="btn btn-danger" onclick="deleteStudent(${student.id})">Xóa</button>
                    </div>
                </td>
            </tr>
        `;
    });

    if (students.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7" style="text-align: center; color: #999;">Chưa có học sinh nào</td></tr>';
    }
}

function updateStudentSelect() {
    const select = document.getElementById('hoc-sinh-select');
    select.innerHTML = '<option value="">-- Chọn học sinh --</option>';

    students.forEach(student => {
        select.innerHTML += `
            <option value="${student.id}">${student.maHS} - ${student.tenHS}</option>
        `;
    });
}

function addStudent() {
    const maHS = document.getElementById('ma-hs').value;
    const tenHS = document.getElementById('ten-hs').value;
    const lop = document.getElementById('lop').value;
    const namSinh = parseInt(document.getElementById('nam-sinh').value);

    const studentData = { maHS, tenHS, lop, namSinh };

    fetchAPI(`${API_BASE}/hoc-sinh`, 'POST', studentData).then(data => {
        showMessage('Thêm học sinh thành công!', 'success');
        resetStudentForm();
        loadStudents();
        loadDashboard();
    }).catch(error => {
        showMessage(error.message || 'Lỗi khi thêm học sinh', 'error');
    });
}

function editStudent(studentId) {
    alert('Tính năng sửa sẽ được bổ sung');
}

function deleteStudent(studentId) {
    if (confirm('Bạn chắc chắn muốn xóa học sinh này?')) {
        fetchAPI(`${API_BASE}/hoc-sinh/${studentId}`, 'DELETE').then(() => {
            showMessage('Xóa học sinh thành công!', 'success');
            loadStudents();
            loadDashboard();
        });
    }
}

function resetStudentForm() {
    document.getElementById('student-form').reset();
}

// ========== SCORES ==========
function loadScores() {
    fetchAPI(`${API_BASE}/diem`, 'GET').then(data => {
        scores = data;
        updateScoresTable();
    });
}

function updateScoresTable() {
    const tbody = document.getElementById('scores-body');
    tbody.innerHTML = '';

    scores.forEach(score => {
        tbody.innerHTML += `
            <tr>
                <td>${score.hocSinh ? score.hocSinh.tenHS : 'N/A'}</td>
                <td>${score.monHoc}</td>
                <td>${score.diemTX}</td>
                <td>${score.diemGK}</td>
                <td>${score.diemCK}</td>
                <td><strong>${(score.diemMonHoc || 0).toFixed(2)}</strong></td>
                <td>
                    <div class="table-actions">
                        <button class="btn btn-edit" onclick="editScore(${score.id})">Sửa</button>
                        <button class="btn btn-danger" onclick="deleteScore(${score.id})">Xóa</button>
                    </div>
                </td>
            </tr>
        `;
    });

    if (scores.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7" style="text-align: center; color: #999;">Chưa có điểm nào</td></tr>';
    }
}

function addScore() {
    const hocSinhId = document.getElementById('hoc-sinh-select').value;
    const monHoc = document.getElementById('mon-hoc').value;
    const diemTX = document.getElementById('diem-tx').value;
    const diemGK = parseFloat(document.getElementById('diem-gk').value);
    const diemCK = parseFloat(document.getElementById('diem-ck').value);

    if (!hocSinhId) {
        showMessage('Vui lòng chọn học sinh!', 'error');
        return;
    }

    const scoreData = { monHoc, diemTX, diemGK, diemCK };

    fetchAPI(`${API_BASE}/diem/hoc-sinh/${hocSinhId}`, 'POST', scoreData).then(data => {
        showMessage('Thêm điểm thành công!', 'success');
        resetScoreForm();
        loadScores();
        loadStudents();
        loadDashboard();
    }).catch(error => {
        showMessage(error.message || 'Lỗi khi thêm điểm', 'error');
    });
}

function editScore(scoreId) {
    alert('Tính năng sửa sẽ được bổ sung');
}

function deleteScore(scoreId) {
    if (confirm('Bạn chắc chắn muốn xóa điểm này?')) {
        fetchAPI(`${API_BASE}/diem/${scoreId}`, 'DELETE').then(() => {
            showMessage('Xóa điểm thành công!', 'success');
            loadScores();
            loadStudents();
            loadDashboard();
        });
    }
}

function resetScoreForm() {
    document.getElementById('score-form').reset();
}

// ========== REPORTS ==========
function loadRankings() {
    const tbody = document.getElementById('rankings-body');
    tbody.innerHTML = '';

    const ranked = students
        .filter(s => s.diemTrungBinh && s.diemTrungBinh > 0)
        .sort((a, b) => b.diemTrungBinh - a.diemTrungBinh);

    ranked.forEach((student, index) => {
        tbody.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${student.maHS}</td>
                <td>${student.tenHS}</td>
                <td>${student.lop}</td>
                <td><strong>${(student.diemTrungBinh || 0).toFixed(2)}</strong></td>
                <td><span class="badge">${student.xepLoai || 'Chưa xếp loại'}</span></td>
            </tr>
        `;
    });

    if (ranked.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" style="text-align: center; color: #999;">Chưa có dữ liệu</td></tr>';
    }
}

function loadSubjectStats() {
    fetchAPI(`${API_BASE}/diem/stats/subjects`, 'GET').then(data => {
        const tbody = document.getElementById('subject-stats-body');
        tbody.innerHTML = '';

        for (const [subject, stats] of Object.entries(data)) {
            tbody.innerHTML += `
                <tr>
                    <td>${subject}</td>
                    <td>${stats.max || 0}</td>
                    <td>${stats.min || 0}</td>
                    <td>${stats.average || 0}</td>
                </tr>
            `;
        }

        if (Object.keys(data).length === 0) {
            tbody.innerHTML = '<tr><td colspan="4" style="text-align: center; color: #999;">Chưa có dữ liệu</td></tr>';
        }
    });
}

// ========== UTILITIES ==========
function fetchAPI(url, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        }
    };

    if (data) {
        options.body = JSON.stringify(data);
    }

    return fetch(url, options)
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {
                    throw new Error(err.error || 'Lỗi từ server');
                });
            }
            return response.json();
        });
}

function showMessage(message, type = 'success') {
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${type}`;
    messageDiv.textContent = message;

    const mainContent = document.querySelector('.main-content');
    mainContent.insertBefore(messageDiv, mainContent.firstChild);

    setTimeout(() => {
        messageDiv.remove();
    }, 5000);
}
