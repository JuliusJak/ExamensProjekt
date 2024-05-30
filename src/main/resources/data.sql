
-- Standard image_questions
INSERT INTO image_question (firstxvalue, firstyvalue, name, question, secondxvalue, secondyvalue)
SELECT 486, 309, 'car_engine', 'Where is the battery?', 642, 237
WHERE NOT EXISTS (SELECT 1 FROM image_question );

-- Standard test_questions
INSERT INTO test_question (category_id, correct_option, optiona, optionb, optionc, optiond, question)
SELECT 1, '0.08%', '0.05%', '0.08%', '0.10%', '0.15%', 'What is the legal blood alcohol concentration (BAC) limit for drivers over 21 in most states?'
    WHERE NOT EXISTS (SELECT 1 FROM test_question)
UNION ALL
SELECT 2, 'Only on open country roads without other cars nearby', 'At all times', 'Only on open country roads without other cars nearby', 'In heavy fog', 'During daylight hours', 'When should you use your high-beam headlights?'
    WHERE NOT EXISTS (SELECT 1 FROM test_question)
UNION ALL
SELECT 3, 'Left arm straight out', 'Left arm straight out', 'Left arm bent up at 90 degrees', 'Left arm bent down at 90 degrees', 'Right arm straight out', 'When riding a bike what is the hand signal for a left turn?'
    WHERE NOT EXISTS (SELECT 1 FROM test_question)
UNION ALL
SELECT 1, 'Stop, regardless of your direction of travel', 'Stop, regardless of your direction of travel', 'Slow down and proceed with caution', 'Continue at the same speed if no children are present', 'Honk to alert the driver of your presence', 'When approaching a school bus with its stop sign extended, you should:'
    WHERE NOT EXISTS (SELECT 1 FROM test_question)
UNION ALL
SELECT 2, 'Stop, then proceed when safe', 'Proceed with caution', 'Stop and do not proceed until the light turns green', 'Stop, then proceed when safe', 'Yield to oncoming traffic', 'What does a flashing red traffic light mean?'
    WHERE NOT EXISTS (SELECT 1 FROM test_question);

-- Standard study_material
INSERT INTO study_material (description, title)
SELECT 'A comprehensive guide to understanding the various traffic signs you will encounter on the road, including regulatory, warning, and informational signs.', 'Understanding Traffic Signs'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'Tips and best practices for safe driving, including defensive driving techniques, handling adverse weather conditions, and avoiding common driving hazards.', 'Safe Driving Practices'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'An explanation of the different road markings, their meanings, and how to interpret them to ensure safe and legal driving.', 'Road Markings and Their Meanings'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'Information on the effects of alcohol on driving abilities, legal BAC limits, and the consequences of driving under the influence.', 'Alcohol and Driving'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'Guidance on how to safely navigate various types of intersections, including those with traffic lights, stop signs, and roundabouts.', 'Navigating Intersections'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'Essential vehicle maintenance tips to ensure your car is in good working condition, including checking tire pressure, oil levels, and understanding dashboard warning lights.', 'Vehicle Maintenance Basics'
    WHERE NOT EXISTS (SELECT 1 FROM study_material)
UNION ALL
SELECT 'Strategies for dealing with common road emergencies such as tire blowouts, brake failure, and accidents.', 'Handling Emergencies on the Road'
    WHERE NOT EXISTS (SELECT 1 FROM study_material);

-- Standard users
INSERT INTO user (id, password, role, username)
SELECT NULL, '123', 'ADMIN', 'bob'
    WHERE NOT EXISTS (SELECT 1 FROM user)
UNION ALL
SELECT NULL, '123', 'ADMIN', 'test'
    WHERE NOT EXISTS (SELECT 1 FROM user)
UNION ALL
SELECT NULL, '123', 'USER', 'lukas'
    WHERE NOT EXISTS (SELECT 1 FROM user)
UNION ALL
SELECT NULL, '123', 'USER', 'samantha'
    WHERE NOT EXISTS (SELECT 1 FROM user)
UNION ALL
SELECT NULL, '123', 'USER', 'oskar'
    WHERE NOT EXISTS (SELECT 1 FROM user);