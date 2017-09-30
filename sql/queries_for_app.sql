/* all symptoms by type_id */
SELECT DISTINCT s.*
FROM `symptoms` s
  INNER JOIN `diseases_symptoms` ds ON s.`id` = ds.`symptom_id`
  INNER JOIN `diseases` d ON ds.`disease_id` = d.`id`
WHERE d.`type_id` = 3;

/* probable diseases and symptoms ids in one row */
SELECT DISTINCT
  d.`id`,
  d.`name`,
  a.`text`,
  (SELECT group_concat(ds2.symptom_id)
   FROM `diseases_symptoms` ds2
   WHERE d.`id` = ds2.`disease_id`) AS symptoms_ids
FROM `diseases` d
  INNER JOIN `diseases_symptoms` ds ON d.`id` = ds.`disease_id`
  INNER JOIN `symptoms` s ON ds.`symptom_id` = s.`id`
  LEFT JOIN `advices` a ON d.`id` = a.disease_id
WHERE s.`id` IN (3, 14, 15, 16) AND d.`type_id` = 5;

/* symptoms ids in one row */
SELECT group_concat(ds2.symptom_id)
FROM `diseases_symptoms` ds2
WHERE 5 = ds2.`disease_id`;

/* count symptoms by disease_id */
SELECT count(s.`id`)
FROM `symptoms` s
  INNER JOIN `diseases_symptoms` ds ON s.`id` = ds.`symptom_id`
  INNER JOIN `diseases` d ON ds.`disease_id` = d.`id`
WHERE d.`id` = 7;

/* symptoms by disease_id */
SELECT s.*
FROM `symptoms` s
  INNER JOIN `diseases_symptoms` ds ON s.`id` = ds.`symptom_id`
  INNER JOIN `diseases` d ON ds.`disease_id` = d.`id`
WHERE d.`id` = 3;

/* diseases types */
SELECT *
FROM `disease_types`;

/* report */
SELECT
  diseases.id                                       AS number,
  diseases.name                                     AS diseases,
  group_concat(symptoms.description SEPARATOR ', ') AS symptoms
FROM diseases
  JOIN diseases_symptoms ON diseases.id = diseases_symptoms.disease_id
  JOIN symptoms ON diseases_symptoms.symptom_id = symptoms.id
GROUP BY number, diseases;