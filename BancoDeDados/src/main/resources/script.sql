--criação do banco de dados

create database ClinicaMedica

--criação das tabelas

create table Doenca(
idDoenca SERIAL,
nome varchar not null,
constraint ID_DOENCA_ID primary key (idDoenca)
);
create table Historico(
idHistorico SERIAL,
tratamentoRecomendado varchar not null,
remediosReceitados varchar not null,
observacoes varchar not null,
idDoenca integer,
idConsulta integer not null,
constraint ID_HISTORICO_ID primary key (idHistorico)
);
create table Consulta(
idConsulta SERIAL,
realizada boolean not null,
dtInit date,
dtFim date,
pago boolean not null,
valorPago numeric not null,
CPFCliente char(10) not null,
CRMMedico char(7) not null,
constraint ID_CONSULTA_ID primary key(idConsulta)
);
create table Paciente(
CPF char(10) not null,
nome varchar not null,
sexo varchar not null,
idade numeric not null,
telefone varchar(9) not null,
endereco varchar not null,
constraint ID_PACIENTE_ID primary key(CPF)
);
create table Medico(
CRM char(7) not null,
nome varchar not null,
telefone varchar(9) not null,
constraint ID_MEDICO_ID primary key(CRM)
);
create table Agenda(
idAgenda SERIAL,
diaSemana varchar not null,
horaInit date not null,
horaFim date not null,
CRMMedico char(7) not null,
constraint ID_AGENDA_ID primary key(idAgenda)
);
create table Especialidade(
codigo SERIAL,
nome varchar not null,
indice numeric not null,
constraint ID_ESPECIALIDADE_ID primary key(codigo)
);
create table ExerceEspecialidade(
CRMMedico char(7),
codEspecialidade integer,
constraint ID_EXERCEESPECIALIDADE_ID primary key(CRMMedico,
codEspecialidade)
);

--chaves estrangeiras
alter table Historico add constraint ID_DOENCA_FK foreign key (idDoenca)
references Doenca On Delete Set Default;
alter table Historico add constraint ID_CONSULTA_FK foreign key (idConsulta)
references Consulta On Delete Set Default;
alter table Consulta add constraint ID_CPF_FK foreign key(CPFCliente)
references Paciente On Delete Set Default;
alter table Consulta add constraint ID_CRM_CONSULTA_FK foreign key(CRMMedico)
references Medico On Delete Set Default;
alter table Agenda add constraint ID_CRM_AGENDA_FK foreign key(CRMMedico)
references Medico On Delete Set Default;
alter table ExerceEspecialidade add constraint ID_CRM_EXERCEESPECIALIDADE_FK
foreign key(CRMMedico)
references Medico On Delete Set Default;
alter table ExerceEspecialidade add constraint ID_CODIGO_EXERCEESPECIALIDADE_FK
foreign key(codEspecialidade)
references Especialidade On Delete Set Default;